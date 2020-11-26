package com.vpc3.personalexpensesapp.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.vpc3.personalexpensesapp.model.Expenses;
import com.vpc3.personalexpensesapp.model.User;

@Database(entities = {User.class, Expenses.class} ,version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DAO getDao();
}
