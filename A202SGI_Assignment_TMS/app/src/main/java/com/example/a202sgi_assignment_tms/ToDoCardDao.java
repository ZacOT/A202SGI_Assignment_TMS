package com.example.a202sgi_assignment_tms;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface ToDoCardDao {

    @Query("SELECT * FROM todolist_table")
    LiveData<List<ToDoCard>> getAllCards();

    @Insert
    void insertToDoCard(ToDoCard... todoCard);

    @Query("DELETE FROM todolist_table WHERE todo_id = (:todo_id)")
    void deleteToDoCard(int todo_id);

}
