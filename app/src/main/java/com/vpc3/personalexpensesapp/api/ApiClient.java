package com.vpc3.personalexpensesapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static String BASE_URL = "https://personalexpensesapp.000webhostapp.com/apiexpenses/";
    public static Retrofit retrofit;

    public static Retrofit getClient() {

        if (retrofit == null) {
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
