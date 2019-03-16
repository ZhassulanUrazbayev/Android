/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package kz.production.kuanysh.tarelka.ui.welcome;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import kz.production.kuanysh.tarelka.data.DataManager;
import kz.production.kuanysh.tarelka.data.network.model.profile.Authorization;
import kz.production.kuanysh.tarelka.ui.base.BasePresenter;
import kz.production.kuanysh.tarelka.utils.rx.SchedulerProvider;

/**
 * Created by janisharali on 27/01/17.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {

    private static final String TAG = "LoginPresenter";

    @Inject
    public LoginPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onPhone(String phone) {
        if (getMvpView().isNetworkConnected()) {
            if(phone==null){
                getMvpView().onError("Ошибка:(");
            }else{
                getCompositeDisposable().add(getDataManager().getApiHelper().getAccessTokenAndLogin(phone)
                        .subscribeOn(getSchedulerProvider().io()).
                                observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<Authorization>() {
                            @Override
                            public void accept(Authorization response) throws Exception {
                                getMvpView().hideLoading();
                                String str="";
                                getDataManager().updateUserInfo(
                                        response.getResult().getToken(),
                                        response.getResult().getId(),
                                        DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                        response.getResult().getFio(),
                                        response.getResult().getStatus(),
                                        response.getResult().getPhone(),
                                        response.getResult().getAvatar(),
                                        response.getResult().getAge(),
                                        response.getResult().getWeight(),
                                        str,
                                        response.getResult().getHeight());

                                if(response.getStatusCode()==200){
                                    getMvpView().openAimsActivity();
                                    getDataManager().setFancyChat("chatf");
                                    getDataManager().setFancyProfile("profilef");
                                    getDataManager().setFancyEducation("educationf");
                                    getDataManager().setFancyQuiz("quizf");
                                }else if(response.getStatusCode()==201){
                                    getDataManager().setFancyChat(null);
                                    getDataManager().setFancyProfile(null);
                                    getDataManager().setFancyEducation(null);
                                    getDataManager().setFancyQuiz(null);
                                    getDataManager().setAims(response.getResult().getGoals().get(0).toString());
                                    getMvpView().openMainActivity();
                                }


                                if (!isViewAttached()) {
                                    return;
                                }


                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                                if (!isViewAttached()) {
                                    return;
                                }

                                getMvpView().hideLoading();

                            }
                        }));
            }

        }else{
            getMvpView().onError("Нет подключения к интернету!");
        }



    }
}
