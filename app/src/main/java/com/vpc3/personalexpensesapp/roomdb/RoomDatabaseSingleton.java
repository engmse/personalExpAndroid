package com.vpc3.personalexpensesapp.roomdb;

import android.content.Context;

import androidx.room.Room;

public class RoomDatabaseSingleton {

    private Context mContext;
    private static RoomDatabaseSingleton mInstatance;
    private AppDatabase appDatabase;
    public RoomDatabaseSingleton(Context mContext) {
        this.mContext = mContext;
        appDatabase = Room.databaseBuilder(mContext,AppDatabase.class,"database_expense").build();
    }
    public static synchronized RoomDatabaseSingleton getInstance(Context context){
        if (mInstatance==null){
            mInstatance = new RoomDatabaseSingleton(context);
        }
        return mInstatance;
    }
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}