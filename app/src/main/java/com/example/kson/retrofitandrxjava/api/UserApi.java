package com.example.kson.retrofitandrxjava.api;

import com.example.kson.retrofitandrxjava.entity.UserEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/09/03
 * Description:
 */
public interface UserApi {

    @GET("user/login")
    Call<UserEntity> login(@Query("mobile") String mobile, @Query("password") String pwd);

    @GET("user/login")
    Call<UserEntity> login(@QueryMap Map<String,String> params);


    @POST("user/login")
    @FormUrlEncoded
    Call<UserEntity> login2(@Field("mobile") String mobile, @Field("password") String pwd);

    @POST("user/login")
    @FormUrlEncoded
    Call<UserEntity> login2(@FieldMap Map<String,String> params);


    @POST("user/login")
    @FormUrlEncoded
    Observable<UserEntity> login3(@FieldMap Map<String,String> params);





}
