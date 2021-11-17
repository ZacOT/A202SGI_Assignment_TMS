package com.example.a202sgi_assignment_tms;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM messages_table ORDER BY message_datetime ASC")
    LiveData<List<Message>> getAllMessages();

    @Insert
    void insertMessage(Message... messages);

    @Delete
    void deleteMessage(Message message);

}
