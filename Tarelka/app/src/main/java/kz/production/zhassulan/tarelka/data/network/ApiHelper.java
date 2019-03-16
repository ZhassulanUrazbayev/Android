package kz.production.kuanysh.tarelka.data.network;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import kz.production.kuanysh.tarelka.data.network.model.admin.AdminInfo;
import kz.production.kuanysh.tarelka.data.network.model.aim.Aim;
import kz.production.kuanysh.tarelka.data.network.model.chat.ChatInfo;
import kz.production.kuanysh.tarelka.data.network.model.goal.SendGoal;
import kz.production.kuanysh.tarelka.data.network.model.main.Main;
import kz.production.kuanysh.tarelka.data.network.model.profile.Authorization;
import kz.production.kuanysh.tarelka.data.network.model.progress.Progress;
import kz.production.kuanysh.tarelka.data.network.model.quiz.Quiz;
import kz.production.kuanysh.tarelka.data.network.model.quizquestions.Questions;
import kz.production.kuanysh.tarelka.data.network.model.quizquestions.QuizResult;
import kz.production.kuanysh.tarelka.data.network.model.rating.Rating;
import kz.production.kuanysh.tarelka.data.network.model.sendmeal.SendMeal;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;

/**
 * Created by User on 24.06.2018.
 */

public interface ApiHelper {
    
    ApiHeader getApiHeader();

    @FormUrlEncoded
    @POST(ApiEndPoint.AUTHORIZATION)
    Single<Authorization> getAccessTokenAndLogin(@Field("phone") String phone);

    @FormUrlEncoded
    @POST(ApiEndPoint.PROFILE)
    Single<Authorization> getProfileInfo(@Field("token") String token);

    @GET(ApiEndPoint.AIM)
    Single<Aim> getAimsApi();

    @GET(ApiEndPoint.FOODS)
    Single<Aim> getFoodsApi();

    @FormUrlEncoded
    @POST(ApiEndPoint.TASKS)
    Single<Main> getMainTasks(@Field("token") String token);

    @FormUrlEncoded
    @POST(ApiEndPoint.PROFILE)
    Single<Authorization> updateProfileInfo(@Field("token") String token, @Field("fio") String name,
                                            @Field("weight") int weight,@Field("age") int age,@Field("height") int height );

    @FormUrlEncoded
    @POST(ApiEndPoint.PROFILE)
    Single<Authorization> updateInfoSuccess(@Field("token") String token,@Field("image") Bitmap imagebase64,
                                            @Field("fio") String fio,@Field("weight") String weight,@Field("age") String age);

    @FormUrlEncoded
    @POST(ApiEndPoint.CHAT)
    Single<ChatInfo> getChats(@Field("token") String token,@Field("page") int currentPage);

    @FormUrlEncoded
    @POST(ApiEndPoint.SEND_MESSAGE)
    Single<ChatInfo> sendMessage(@Field("token") String token,@Field("message") String message,@Field("sended_date") String sended_date
            ,@Field("sended_time") String sended_time);

    @FormUrlEncoded
    @POST(ApiEndPoint.LIST_QUIZ)
    Single<Quiz> getQuizList(@Field("token") String token);


    @Multipart
    @POST(ApiEndPoint.PROFILE)
    Single<Authorization> updateProfileInfo(@Part("token") RequestBody token,
                                                @Part MultipartBody.Part image,
                                                @Part("fio") String fio,
                                                @Part("weight") int weight,
                                                @Part("age") int age,
                                            @Part("height") int height);

    @FormUrlEncoded
    @POST(ApiEndPoint.LIST_QUESTIONS)
    Single<Questions> getQuestions(@Field("token") String token,@Field("quiz_id") String quiz_id);

    @FormUrlEncoded
    @POST(ApiEndPoint.SEND_RESULTS)
    Single<QuizResult> sendQuizResults(@Field("token") String token,
                                       @Field("quiz_id") int quiz_id,
                                       @Field("max_answer") int max_answer,
                                       @Field("correct_answer") int correct_answer,
                                       @Field("quiz_date") String date);

    @Multipart
    @POST(ApiEndPoint.SEND_MESSAGE)
    Single<kz.production.kuanysh.tarelka.data.network.model.chat.receive.Result> sendImageMessage(@Part("token") RequestBody token,
                                                                                                  @Part("images") RequestBody description,
                                                                                                  @Part List<MultipartBody.Part> image);



    @FormUrlEncoded
    @POST(ApiEndPoint.SEND_GOAL)
    Single<SendGoal> sendGoal(@Field("token") String token,
                              @Field("goal_id") int id);

    @FormUrlEncoded
    @POST(ApiEndPoint.SEND_MEALS)
    Single<SendMeal> sendMeals(@FieldMap Map<String,String> meals);

    @FormUrlEncoded
    @POST(ApiEndPoint.SEND_MEALS_FAVOURITE)
    Single<SendMeal> sendMealsFavourite(@FieldMap Map<String,String> meals);

    @FormUrlEncoded
    @POST(ApiEndPoint.GET_PROGRESS)
    Single<Progress> getProgress(@Field("token") String token);

    @GET(ApiEndPoint.ADMIN_INFO)
    Single<AdminInfo> getAdminInfo();

    @FormUrlEncoded
    @POST(ApiEndPoint.GET_RATING)
    Single<Rating> getRating(@Field("token") String token);



}
