package com.vpc3.personalexpensesapp.api;

import com.vpc3.personalexpensesapp.api.reponse.CommonResponse;
import com.vpc3.personalexpensesapp.api.reponse.LoginResponse;
import com.vpc3.personalexpensesapp.api.request.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("register.php")
    Call<CommonResponse> register(@Body User user);

    @FormUrlEncoded
    @POST("register.php")
    Call<CommonResponse> register(@Field("username") String userName,
                                  @Field("mobile") String mobile,
                                  @Field("password") String password,
                                  @Field("conpassword") String cpassword,
                                  @Field("email") String email);
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(@Field("username") String userName,
                              @Field("password") String password);
}