package com.vpc3.personalexpensesapp.activites.model;

public class Expenses {
   private String place,date;
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
