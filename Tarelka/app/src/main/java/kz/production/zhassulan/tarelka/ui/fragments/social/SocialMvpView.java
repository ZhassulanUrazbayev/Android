package kz.production.kuanysh.tarelka.ui.fragments.social;

import kz.production.kuanysh.tarelka.ui.base.MvpView;

/**
 * Created by User on 23.07.2018.
 */

public interface SocialMvpView extends MvpView {

    void getAdminInfo(String whats,String telegram,String facebook,String viber,String vk);

    void openWhatsapp();

    void openTelegram();

    void openVK();

    void openFacebook();

    void openViber();

    void openBack();

}
