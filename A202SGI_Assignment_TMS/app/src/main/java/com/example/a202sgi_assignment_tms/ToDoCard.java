package com.example.a202sgi_assignment_tms;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "todolist_table")
public class ToDoCard {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int todo_id;

    @ColumnInfo(name = "todo_title")
    public String todo_title;

    @ColumnInfo(name = "todo_description")
    public String todo_description;

    @ColumnInfo(name = "todo_startdate")
    @TypeConverters({TDCDateConverter.class})
    public Date todo_startdate;

    @ColumnInfo(name = "todo_duedate")
    @TypeConverters({TDCDateConverter.class})
    public Date todo_duedate;

    @ColumnInfo(name = "todo_status")
    public String todo_status;

    @ColumnInfo(name = "todo_creator")
    @NonNull
    public int creator_id;


}