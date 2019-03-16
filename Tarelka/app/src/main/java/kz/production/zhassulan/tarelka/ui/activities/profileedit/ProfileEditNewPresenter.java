package kz.production.kuanysh.tarelka.ui.activities.profileedit;

import android.text.TextUtils;
import android.util.Log;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import kz.production.kuanysh.tarelka.data.DataManager;
import kz.production.kuanysh.tarelka.data.network.model.profile.Authorization;
import kz.production.kuanysh.tarelka.ui.base.BasePresenter;
import kz.production.kuanysh.tarelka.utils.rx.SchedulerProvider;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by User on 20.07.2018.
 */

public class ProfileEditNewPresenter<V extends ProfileEditNewMvpView> extends BasePresenter<V>
        implements ProfileEditNewMvpPresenter<V> {

    @Inject
    public ProfileEditNewPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onSaveClick(MultipartBody.Part image64, String name, String age, String weight,String  height) {
        if (getMvpView().isNetworkConnected()) {
            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(name) || TextUtils.isEmpty(weight) || TextUtils.isEmpty(height)){
                getMvpView().showMessage("Заполните все данные!");
            }else{
                Log.d("edit", "onSaveClick: ");
                RequestBody body =
                        RequestBody.create(MediaType.parse("text/plain"), getDataManager().getAccessToken());

                if(getDataManager().getAccessToken()!=null){
                    getMvpView().showLoading();
                    Log.d("edit", "onSaveClick: access is not null ");
                    getCompositeDisposable().add(getDataManager().getImageApiHelper().updateProfileInfo(
                            body,image64,name,Integer.valueOf(weight),Integer.valueOf(age),Integer.valueOf(height))
                            .subscribeOn(getSchedulerProvider().io()).
                                    observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<Authorization>() {
                                @Override
                                public void accept(Authorization response) throws Exception {
                                    Log.d("edit", "accept: ");
                                  //  getMvpView().showMessage("Успешно изменено!");
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
                                            getDataManager().getAims(),
                                            response.getResult().getHeight());

                                    if (!isViewAttached()) {
                                        return;
                                    }

                                    getMvpView().hideLoading();

                                    getMvpView().openProfileFragment();

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {

                                    if (!isViewAttached()) {
                                        return;
                                    }

                                    if(throwable.getMessage() != null)
                                        Log.i("MESS", throwable.getMessage());

                                    if(throwable.getCause() != null)
                                        throwable.printStackTrace();


                                    getMvpView().hideLoading();


                                }
                            }));
                }else{
                    getMvpView().showMessage("Token is required");
                }

            }

        }else{
            getMvpView().onError("Нет подключения к интернету!");
        }



    }

    @Override
    public void onSaveClickWithoutImage(String name, String age, String weight, String height) {
        if (getMvpView().isNetworkConnected()) {
            if(getDataManager().getAccessToken()!=null){
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(name) || TextUtils.isEmpty(weight) || TextUtils.isEmpty(height)){
                    getMvpView().showMessage("Заполните все данные!");
                }else{
                    getCompositeDisposable().add(getDataManager().getApiHelper().updateProfileInfo(
                            getDataManager().getAccessToken(),name,Integer.valueOf(weight),Integer.valueOf(age),
                            Integer.valueOf(height))
                            .subscribeOn(getSchedulerProvider().io()).
                                    observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<Authorization>() {
                                @Override
                                public void accept(Authorization response) throws Exception {
                                 //   getMvpView().showMessage("Успешно изменено!");
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
                                            getDataManager().getAims(),
                                            response.getResult().getHeight());

                                    if (!isViewAttached()) {
                                        return;
                                    }

                                    getMvpView().hideLoading();

                                    getMvpView().openProfileFragment();

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {

                                    if (!isViewAttached()) {
                                        return;
                                    }

                                    if(throwable.getMessage() != null)
                                        Log.i("MESS", throwable.getMessage());

                                    if(throwable.getCause() != null)
                                        throwable.printStackTrace();

                                    getMvpView().hideLoading();

                                }
                            }));
                }

            }else{
                getMvpView().showMessage("Token is required");
            }
        }else{
            getMvpView().onError("Нет подключения к интернету!");
        }

    }

    @Override
    public void onAddPhotoClick() {
        getMvpView().openImagePicker();
    }

    @Override
    public void onBackClick() {
        getMvpView().openProfileFragment();
    }

    @Override
    public void onEditCardClick() {
        getMvpView().openAimsEditActivity();
    }
}
