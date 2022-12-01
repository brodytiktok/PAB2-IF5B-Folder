package com.if5b.myapplication.Services;

import com.if5b.myapplication.Models.ValueData;
import com.if5b.myapplication.Models.ValueNoData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @POST("loginUser")
    @FormUrlEncoded
    Call<ValueNoData> login(@Field("key") String key,
                            @Field("username") String username,
                            @Field("password") String password);

    @POST("registerUser")
    @FormUrlEncoded
    Call<ValueNoData> register(@Field("key") String key,
                               @Field("username") String username,
                               @Field("password") String password);

    @POST("getAllPost")
    @FormUrlEncoded
    Call<ValueData<POST>> getPost(@Field("key") String key);

    @POST("updatePost")
    @FormUrlEncoded
    Call<ValueNoData> updatePost(@Field("key") String key,
                                 @Field("id") int id,
                                 @Field("content") String content);

    @POST("deletePost")
    @FormUrlEncoded
    Call<ValueNoData> deletePost(@Field("key") String key,
                                 @Field("id") int id);
}
