package kz.production.kuanysh.tarelka.ui.welcome;

import kz.production.kuanysh.tarelka.di.PerActivity;
import kz.production.kuanysh.tarelka.ui.base.MvpPresenter;

/**
 * Created by User on 03.07.2018.
 */
@PerActivity
public interface CreateAimMvpPresenter<V extends CreateAimMvpView> extends MvpPresenter<V> {

    void onNextClick(int aimId);

    void onViewPrepared();

    void onBackClick(int aimId);
}
