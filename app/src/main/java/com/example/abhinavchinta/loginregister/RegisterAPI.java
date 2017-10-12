package com.example.abhinavchinta.loginregister;


import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Abhinav Chinta on 10/12/2017.
 */

public interface RegisterAPI {
    @FormUrlEncoded
    @POST("/register.php")
    public void insertUser(
            @Field("name") String name,
            @Field("username") String username,
            @Field("password") String password,
            @Field("age") int age,
            Callback<Response> callback);


}
