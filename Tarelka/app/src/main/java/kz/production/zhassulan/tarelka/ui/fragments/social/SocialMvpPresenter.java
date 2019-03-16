package kz.production.kuanysh.tarelka.ui.fragments.social;

import kz.production.kuanysh.tarelka.di.PerActivity;
import kz.production.kuanysh.tarelka.ui.base.MvpPresenter;
import kz.production.kuanysh.tarelka.ui.fragments.ChatMvpView;

/**
 * Created by User on 23.07.2018.
 */
@PerActivity
public interface SocialMvpPresenter<V extends SocialMvpView> extends MvpPresenter<V> {

    void onViewPrepared();

    void onTelegramClick();

    void onWhatsappClick();

    void onViberClick();

    void onFacebookClick();

    void onVkClick();

    void onBackClick();
}
