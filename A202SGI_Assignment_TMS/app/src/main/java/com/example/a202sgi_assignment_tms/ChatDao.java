package com.example.a202sgi_assignment_tms;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ChatDao {

    @Query("SELECT * FROM chat_table")
    LiveData<List<Chat>> getAllChat();

    @Insert
    void insertChat(Chat... chat);
}
