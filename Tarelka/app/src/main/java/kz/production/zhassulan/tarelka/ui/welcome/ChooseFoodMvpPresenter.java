package kz.production.kuanysh.tarelka.ui.welcome;

import java.util.HashSet;

import kz.production.kuanysh.tarelka.di.PerActivity;
import kz.production.kuanysh.tarelka.ui.base.MvpPresenter;

/**
 * Created by User on 03.07.2018.
 */
@PerActivity
public interface ChooseFoodMvpPresenter<V extends ChooseFoodMvpView> extends MvpPresenter<V> {

    void onViewPrepared();

    void onNextClick(HashSet<Integer> listId);

    void onSendFavourite(HashSet<Integer> listId);
}
