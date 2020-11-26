package com.vpc3.personalexpensesapp.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vpc3.personalexpensesapp.model.Expenses;
import com.vpc3.personalexpensesapp.model.User;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DAO {

    @Insert
    void insertUser(User user);

    @Query("select * from User where username = :userName and password = :password ")
    User login(String userName,String password);

    @Query("select * from User")
    List<User> getAllUsers();

    @Insert
    void addExpenses(Expenses expenses);

    @Query("select * from Expenses where uid = :puid")
    List<Expenses> getAllExpenses(int puid);

    @Query("select * from Expenses where txDate = :date and uid=:uid")
    List<Expenses> getAllExpenseByDate(String date,int uid);

    @Delete
    void delete(Expenses expenses);

    @Update
    void UpdateExpenses(Expenses expenses);
}
