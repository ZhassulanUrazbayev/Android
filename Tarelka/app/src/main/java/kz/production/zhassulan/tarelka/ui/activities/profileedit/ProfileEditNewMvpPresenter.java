package kz.production.kuanysh.tarelka.ui.activities.profileedit;

import kz.production.kuanysh.tarelka.di.PerActivity;
import kz.production.kuanysh.tarelka.ui.base.MvpPresenter;
import okhttp3.MultipartBody;

/**
 * Created by User on 20.07.2018.
 */
@PerActivity
public interface ProfileEditNewMvpPresenter <V extends ProfileEditNewMvpView> extends MvpPresenter<V> {

    void onSaveClick(MultipartBody.Part image64, String name, String age, String weight, String height);

    void onSaveClickWithoutImage(String name, String age, String weight,String height);

    void onAddPhotoClick();

    void onBackClick();

    void onEditCardClick();
}
