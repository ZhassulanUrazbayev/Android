package kz.production.kuanysh.tarelka.ui.welcome;


import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import kz.production.kuanysh.tarelka.data.DataManager;
import kz.production.kuanysh.tarelka.data.network.model.aim.Aim;
import kz.production.kuanysh.tarelka.data.network.model.goal.SendGoal;
import kz.production.kuanysh.tarelka.ui.base.BasePresenter;
import kz.production.kuanysh.tarelka.utils.rx.SchedulerProvider;

/**
 * Created by User on 03.07.2018.
 */

public class CreateAimPresenter<V extends CreateAimMvpView> extends BasePresenter<V>
        implements CreateAimMvpPresenter<V>  {

    @Inject
    public CreateAimPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onNextClick(int aimId) {
        if (getMvpView().isNetworkConnected()) {
            getMvpView().showLoading();
            getCompositeDisposable().add(getDataManager()
                    .getApiHelper().sendGoal(getDataManager().getAccessToken(),aimId)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Consumer<SendGoal>() {
                        @Override
                        public void accept(@NonNull SendGoal response)
                                throws Exception {
                            getMvpView().hideLoading();
                            getDataManager().updateUserInfo(
                                    response.getResult().getToken(),
                                    response.getResult().getId(),
                                    DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                    "",
                                    response.getResult().getStatus(),
                                    response.getResult().getPhone(),
                                    response.getResult().getAvatar(),
                                    String.valueOf(""),
                                    String.valueOf(""),
                                    response.getResult().getGoals().get(0).toString(),
                                    String.valueOf(""));
                            getMvpView().openFoodsActivity();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable)
                                throws Exception {
                            if (!isViewAttached()) {
                                return;
                            }

                            getMvpView().showMessage("Aims post error!");

                            getMvpView().hideLoading();

                        }
                    }));
        }else{
            getMvpView().onError("Нет подключения к интернету!");
        }

    }

    @Override
    public void onViewPrepared() {
        if (getMvpView().isNetworkConnected()) {
            getMvpView().showLoading();
            getCompositeDisposable().add(getDataManager()
                    .getApiHelper().getAimsApi()
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Consumer<Aim>() {
                        @Override
                        public void accept(@NonNull Aim aim)
                                throws Exception {
                            getMvpView().updateAims(aim.getResult());

                            getMvpView().hideLoading();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable)
                                throws Exception {
                            if (!isViewAttached()) {
                                return;
                            }

                            getMvpView().showMessage("Aims got error!");

                            getMvpView().hideLoading();

                            //
                        }
                    }));
        }else{
            getMvpView().onError("Нет подключения к интернету!");
        }

    }

    @Override
    public void onBackClick(int aimId) {
        getCompositeDisposable().add(getDataManager()
                .getApiHelper().sendGoal(getDataManager().getAccessToken(),aimId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<SendGoal>() {
                    @Override
                    public void accept(@NonNull SendGoal response)
                            throws Exception {
                        if(response.getStatusCode()==200){
                            getDataManager().updateUserInfo(
                                    getDataManager().getAccessToken(),
                                    Integer.valueOf(getDataManager().getCurrentUserId().toString()),
                                    DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                    getDataManager().getCurrentUserName(),
                                    getDataManager().getCurrentStatus(),
                                    getDataManager().getPhoneNumber(),
                                    getDataManager().getImage(),
                                    getDataManager().getAge(),
                                    getDataManager().getWeight(),
                                    response.getResult().getGoals().get(0).toString(),
                                    getDataManager().getHeight());
                            getMvpView().openProfileEditActivity();


                        }else {
                            getMvpView().openProfileEditActivity();
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }



                    }
                }));
    }
}
