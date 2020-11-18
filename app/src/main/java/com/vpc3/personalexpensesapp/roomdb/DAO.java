package com.vpc3.personalexpensesapp.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vpc3.personalexpensesapp.model.User;

import java.util.List;

@Dao
public interface DAO {

    @Insert
    void insertUser(User user);

    @Query("select * from User where username = :userName and password = :password ")
    User login(String userName,String password);

    @Query("select * from User")
    List<User> getAllUsers();
}
