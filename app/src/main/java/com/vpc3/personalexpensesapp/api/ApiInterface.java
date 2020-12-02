package com.vpc3.personalexpensesapp.api;

import com.vpc3.personalexpensesapp.api.reponse.CommonResponse;
import com.vpc3.personalexpensesapp.api.reponse.ExpensesResponse;
import com.vpc3.personalexpensesapp.api.reponse.LoginResponse;
import com.vpc3.personalexpensesapp.api.request.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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


    @FormUrlEncoded
    @POST("expenses.php")
    Call<CommonResponse> addExpenses(@Field("place_txt") String place,
                                     @Field("pay_date") String date,
                                     @Field("amount") String amount,
                                     @Field("user_id") String userId
    );

    @GET("expenses.php")
    Call<ExpensesResponse> getAllExpenses(@Query("user_id") String userId,
                                          @Query("pay_date") String date );
}