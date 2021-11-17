package com.example.a202sgi_assignment_tms;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    private UserDao mUserDao;
    private LiveData<List<User>> mAllUser;

    UserRepository(Application application){
        MainDatabase db = MainDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mAllUser = mUserDao.getAllUsers();
    }

    LiveData<List<User>> getAllUser(){
        return mAllUser;
    }


}
