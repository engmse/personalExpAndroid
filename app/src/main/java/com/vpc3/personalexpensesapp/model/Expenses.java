package com.vpc3.personalexpensesapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Expenses {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("place_txt")
    @Expose
    private String place;
    @SerializedName("pay_date")
    @Expose
    private String date;
    @SerializedName("amount")
    @Expose
    private double money;


    public Expenses(String place, String date, double money) {
        this.place = place;
        this.date = date;
        this.money = money;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
