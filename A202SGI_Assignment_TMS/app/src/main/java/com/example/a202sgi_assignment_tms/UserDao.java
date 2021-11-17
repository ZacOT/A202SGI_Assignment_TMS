package com.example.a202sgi_assignment_tms;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getAllUsers();

    @Insert
    void registerUser(User user);

    @Query("SELECT * FROM user_table WHERE user_email=(:user_email) and password=(:password)")
    User login(String user_email, String password);


    /*@Query("SELECT user_name FROM user_table WHERE user_id=(:user_id)")
    LiveData<String> getUserName(int user_id);*/
}
