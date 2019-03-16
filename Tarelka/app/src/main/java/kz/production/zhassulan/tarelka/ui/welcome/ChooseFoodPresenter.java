package kz.production.kuanysh.tarelka.ui.welcome;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import kz.production.kuanysh.tarelka.data.DataManager;
import kz.production.kuanysh.tarelka.data.network.model.aim.Aim;
import kz.production.kuanysh.tarelka.data.network.model.goal.SendGoal;
import kz.production.kuanysh.tarelka.data.network.model.sendmeal.SendMeal;
import kz.production.kuanysh.tarelka.ui.base.BasePresenter;
import kz.production.kuanysh.tarelka.utils.rx.SchedulerProvider;

/**
 * Created by User on 03.07.2018.
 */

public class ChooseFoodPresenter<V extends ChooseFoodMvpView> extends BasePresenter<V>
        implements ChooseFoodMvpPresenter<V>  {

    @Inject
    public ChooseFoodPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onViewPrepared() {
        if (getMvpView().isNetworkConnected()) {
            getMvpView().showLoading();
            getCompositeDisposable().add(getDataManager()
                    .getApiHelper().getFoodsApi()
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Consumer<Aim>() {
                        @Override
                        public void accept(@NonNull Aim aim)
                                throws Exception {
                            getMvpView().updateFoods(aim.getResult());

                            getMvpView().hideLoading();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable)
                                throws Exception {
                            if (!isViewAttached()) {
                                return;
                            }

                            getMvpView().showMessage("Foods got error!");

                            getMvpView().hideLoading();

                        }
                    }));
        }else{
            getMvpView().onError("Нет подключения к интернету!");
        }

    }

    @Override
    public void onNextClick(HashSet<Integer> listId) {
        if (getMvpView().isNetworkConnected()) {
            ArrayList<Integer> newList=new ArrayList<>();
            newList.addAll(listId);

            Map<String, String> params = new HashMap<>();
            params.put("token",getDataManager().getAccessToken());

            for(int i=0;i<newList.size();i++){
                params.put("meals[" + i + "]",String.valueOf(newList.get(i)));
            }

            getMvpView().showLoading();
            getCompositeDisposable().add(getDataManager()
                    .getApiHelper().sendMeals(params)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Consumer<SendMeal>() {
                        @Override
                        public void accept(@NonNull SendMeal meal)
                                throws Exception {
                            getMvpView().hideLoading();

                            if(meal.getStatusCode()==200){
                                //getMvpView().showMessage("Foods post successfull!");
                                //getMvpView().openLoginActivity();
                            }else if(meal.getStatusCode()==201){
                               // getMvpView().showMessage("Это блюдо уже выбрано");
                            }


                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable)
                                throws Exception {
                            if (!isViewAttached()) {
                                return;
                            }

                            getMvpView().showMessage("Возникло ошибка!");
                            getMvpView().hideLoading();

                        }
                    }));
        }else{
            getMvpView().onError("Нет подключения к интернету!");
        }

    }

    @Override
    public void onSendFavourite(HashSet<Integer> listId) {
        if (getMvpView().isNetworkConnected()) {
            ArrayList<Integer> newList=new ArrayList<>();
            newList.addAll(listId);

            Map<String, String> params = new HashMap<>();
            params.put("token",getDataManager().getAccessToken());

            for(int i=0;i<newList.size();i++){
                params.put("meals[" + i + "]",String.valueOf(newList.get(i)));
            }

            getMvpView().showLoading();
            getCompositeDisposable().add(getDataManager()
                    .getApiHelper().sendMealsFavourite(params)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Consumer<SendMeal>() {
                        @Override
                        public void accept(@NonNull SendMeal meal)
                                throws Exception {
                            getMvpView().hideLoading();

                            if(meal.getStatusCode()==200){
                                getMvpView().openMainActivity();
                            }else if(meal.getStatusCode()==201){
                                // getMvpView().showMessage("Это блюдо уже выбрано");
                            }


                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable)
                                throws Exception {
                            if (!isViewAttached()) {
                                return;
                            }

                            getMvpView().showMessage("Возникло ошибка!");
                            getMvpView().hideLoading();

                        }
                    }));
        }else{
            getMvpView().onError("Нет подключения к интернету!");
        }
    }
}
