package kz.production.kuanysh.tarelka.ui.welcome;

import java.util.List;

import kz.production.kuanysh.tarelka.data.network.model.aim.Result;
import kz.production.kuanysh.tarelka.ui.base.MvpView;

/**
 * Created by User on 03.07.2018.
 */

public interface ChooseFoodMvpView extends MvpView {

    void updateFoods(List<Result> list);

    void openMainActivity();

}
