package com.hfad.firstmidproject.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiHelper {
    @GET("latest")
    Call<ExchangeValue> getExchange(@Query("base") String value);
}
