package kz.production.kuanysh.tarelka.ui.fragments;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import kz.production.kuanysh.tarelka.data.DataManager;
import kz.production.kuanysh.tarelka.ui.base.BasePresenter;
import kz.production.kuanysh.tarelka.utils.rx.SchedulerProvider;

/**
 * Created by User on 26.06.2018.
 */

public class ProfilePresenter<V extends ProfileMvpView> extends BasePresenter<V>
        implements ProfileMvpPresenter<V> {


    @Inject
    public ProfilePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onEditClick() {
        getMvpView().openEditFragment();

    }

    @Override
    public void onViewPrepared() {
        if(getDataManager().getAccessToken()!=null){

            getMvpView().updateInfo(getDataManager().getCurrentUserName(),getDataManager().getCurrentStatus()
            ,getDataManager().getPhoneNumber(),
                    getDataManager().getAge(),
                    getDataManager().getWeight(),
                    getDataManager().getAims(),
                    getDataManager().getImage(),
                    getDataManager().getHeight());
        }
        else{
            getMvpView().showMessage("Something went wrong!");
        }


    }

    @Override
    public void onExitClick() {
        getMvpView().openDialog();
    }
}
