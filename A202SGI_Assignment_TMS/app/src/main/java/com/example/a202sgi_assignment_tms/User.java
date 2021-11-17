package com.example.a202sgi_assignment_tms;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int user_id;

    @ColumnInfo(name = "user_email")
    public String user_email;

    @ColumnInfo(name = "user_name")
    public String user_name;

    @ColumnInfo (name = "password")
    public String password;


    //Setters
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Getters
    public int getUser_id() {
        return user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }
}
