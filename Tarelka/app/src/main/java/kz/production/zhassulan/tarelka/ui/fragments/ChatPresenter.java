package kz.production.kuanysh.tarelka.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.util.Log;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import id.zelory.compressor.Compressor;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import kz.production.kuanysh.tarelka.data.DataManager;
import kz.production.kuanysh.tarelka.data.network.model.chat.ChatInfo;
import kz.production.kuanysh.tarelka.ui.base.BasePresenter;
import kz.production.kuanysh.tarelka.utils.rx.SchedulerProvider;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by User on 26.06.2018.
 */

public class ChatPresenter<V extends ChatMvpView> extends BasePresenter<V>
        implements ChatMvpPresenter<V> {

    @Inject
    public ChatPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onSendClick(String message,int currentPage) {
        if (getMvpView().isNetworkConnected()) {
            if(getDataManager().getAccessToken()!=null){
                if(message.length()!=0){

                    DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                    Date date=new Date();

                    getCompositeDisposable().add(getDataManager()
                            .getApiHelper().sendMessage(getDataManager().getAccessToken(),message,dateFormat.format(date),
                                    new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()))
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<ChatInfo>() {
                                @Override
                                public void accept(@NonNull ChatInfo blogResponse)
                                        throws Exception {

                                    //getMvpView().showMessage("Message sent!");
                                    onViewPrepared(currentPage);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable)
                                        throws Exception {
                                    if (!isViewAttached()) {
                                        return;
                                    }

                                    getMvpView().showMessage("Не удалось отправить сообщение!Проверьте свое подключение к интернету");


                                }
                            }));
                }else{

                }

            }
            else{
                getMvpView().showMessage("Что-то пошло не так! Проверьте свое подключение к интернету");
            }

        }else{
            getMvpView().onError("Нет подключения к интернету!");
        }


    }

    @Override
    public void onSendImage(List<Uri> uri, List<String> path, Context context,int currentPage) {
        if (getMvpView().isNetworkConnected()) {
            if(path!=null && uri !=null){
                List<MultipartBody.Part> images=new ArrayList<>();
                RequestBody body =
                        RequestBody.create(MediaType.parse("text/plain"), getDataManager().getAccessToken());
                for(int i=0;i<uri.size();i++){
                    Log.i("PATH", uri.get(i).toString());
                    File file = new File(path.get(i));
                    try {
                        File compressedImageFile = new Compressor(context).compressToFile(file);
                        RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), compressedImageFile);
                        //RequestBody filePart= RequestBody.create(MediaType.parse(context.getContentResolver().getType(uri.get(i))), file);
                        MultipartBody.Part part =
                                MultipartBody.Part.createFormData("images["+i+"]", file.getName(), fbody);
                        images.add(part);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


                String descriptionString = "images";
                RequestBody description =
                        RequestBody.create(
                                okhttp3.MultipartBody.FORM, descriptionString);

                if(getDataManager().getAccessToken()!=null){
                    getMvpView().showLoading();
                    getCompositeDisposable().add(getDataManager()
                            .getImageApiHelper().sendImageMessage(body,description,images)
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<kz.production.kuanysh.tarelka.data.network.model.chat.receive.Result>() {
                                @Override
                                public void accept(@NonNull kz.production.kuanysh.tarelka.data.network.model.chat.receive.Result
                                                           blogResponse)
                                        throws Exception {

                                    getMvpView().hideLoading();
                                    //onViewPrepared(currentPage);
                                    getMvpView().openCommentDialog();
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable)
                                        throws Exception {
                                    if (!isViewAttached()) {
                                        return;
                                    }
                                    getMvpView().hideLoading();
                                    getMvpView().showMessage("Не удалось отправить изображение!");
                                    Log.d("myTag", "accept:error "+ throwable.getMessage());


                                }
                            }));
                }
                else{
                    getMvpView().showMessage("Что-то пошло не так!Проверьте свое подключение к интернету");
                }

            }
            else{
                getMvpView().showMessage("Uri and filePath are null");
            }

        }else {
            getMvpView().onError("Нет подключения к интернету!");
        }


    }


    @Override
    public void onImageclick() {
        getMvpView().openImagePicker();
    }



    @Override
    public void onMailClick() {
        getMvpView().openSendAsSocial();
    }

    @Override
    public void onViewPrepared(int pagenumber) {
        if (getMvpView().isNetworkConnected()) {
            if(getDataManager().getAccessToken()!=null){
                getCompositeDisposable().add(getDataManager()
                        .getApiHelper().getChats(getDataManager().getAccessToken(),pagenumber)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<ChatInfo>() {
                            @Override
                            public void accept(@NonNull ChatInfo blogResponse)
                                    throws Exception {
                                getMvpView().hideKeyboard();

                                getMvpView().updateChat(blogResponse.getResult().getChats(),blogResponse.getResult().getCountPages());
                                //getMvpView().showMessage("Accessed");
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
            else{
                getMvpView().showMessage("Что-то пошло не так!Проверьте свое подключение к интернету");
            }
        }else {
            getMvpView().onError("Нет подключения к интернету!");
        }



    }
}
