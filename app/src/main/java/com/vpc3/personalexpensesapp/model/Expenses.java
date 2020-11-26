package com.vpc3.personalexpensesapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Expenses {

    @PrimaryKey(autoGenerate = true)
    private int txId;
    @ColumnInfo(name = "Place")
    private String place;
    @ColumnInfo(name = "txDate")
    private String date;
    @ColumnInfo(name = "Amount")
    private double money;
    @ColumnInfo(name = "uid")
    private int uid;

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

    public int getTxId() {
        return txId;
    }

    public void setTxId(int txId) {
        this.txId = txId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
