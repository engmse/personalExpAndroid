package com.vpc3.personalexpensesapp.api.reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vpc3.personalexpensesapp.api.request.User;

public class LoginResponse extends CommonResponse{

    @SerializedName("data")
    @Expose
    private User data;


    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
