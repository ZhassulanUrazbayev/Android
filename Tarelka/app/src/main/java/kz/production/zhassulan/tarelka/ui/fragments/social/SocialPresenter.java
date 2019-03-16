package kz.production.kuanysh.tarelka.ui.fragments.social;


import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import kz.production.kuanysh.tarelka.data.DataManager;
import kz.production.kuanysh.tarelka.data.network.model.admin.AdminInfo;
import kz.production.kuanysh.tarelka.data.network.model.main.Main;
import kz.production.kuanysh.tarelka.ui.base.BasePresenter;
import kz.production.kuanysh.tarelka.ui.fragments.ChatMvpPresenter;
import kz.production.kuanysh.tarelka.ui.fragments.ChatMvpView;
import kz.production.kuanysh.tarelka.utils.rx.SchedulerProvider;

/**
 * Created by User on 23.07.2018.
 */

public class SocialPresenter<V extends SocialMvpView> extends BasePresenter<V>
        implements SocialMvpPresenter<V> {


    @Inject
    public SocialPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        if (getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(getDataManager()
                    .getApiHelper().getAdminInfo()
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Consumer<AdminInfo>() {
                        @Override
                        public void accept(@NonNull AdminInfo blogResponse)
                                throws Exception {
                            getMvpView().hideLoading();

                            if(blogResponse.getStatusCode()==200){
                                getMvpView().getAdminInfo(blogResponse.getResult().getWhatsapp(),
                                        blogResponse.getResult().getTelegram(),
                                        blogResponse.getResult().getFacebook(),
                                        blogResponse.getResult().getViber(),
                                        blogResponse.getResult().getVk());
                            }else if(blogResponse.getStatusCode()==404){
                                getMvpView().showMessage(blogResponse.getMessage());
                            }


                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable)
                                throws Exception {
                            if (!isViewAttached()) {
                                return;
                            }
                            getMvpView().hideLoading();

                            getMvpView().showMessage("Tasks get error!");


                        }
                    }));
        }else{
            getMvpView().onError("Нет подключения к интернету!");
        }

    }

    @Override
    public void onTelegramClick() {
        getMvpView().openTelegram();
    }

    @Override
    public void onWhatsappClick() {
        getMvpView().openWhatsapp();
    }

    @Override
    public void onViberClick() {
        getMvpView().openViber();
    }

    @Override
    public void onFacebookClick() {
        getMvpView().openFacebook();
    }

    @Override
    public void onVkClick() {
        getMvpView().openVK();
    }

    @Override
    public void onBackClick() {
        getMvpView().openBack();
    }
}
