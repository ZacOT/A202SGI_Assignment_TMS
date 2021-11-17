package com.example.a202sgi_assignment_tms;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;


@Entity(tableName = "messages_table",
        foreignKeys = {@ForeignKey(entity = User.class,
        parentColumns = "user_id",
        childColumns = "author_id")}
        /*@ForeignKey(entity = Chat.class,
        parentColumns = "chat_id",
        childColumns = "chat_id")}*/)
public class Message {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "message_id")
    @NonNull
    public int message_id;

    @ColumnInfo(name = "chat_id")
    public int chat_id;

    @ColumnInfo(name = "author_id")
    @NonNull
    public int author_id;

    @ColumnInfo(name = "message_content")
    @NonNull
    public String message_content;

    @ColumnInfo(name = "message_datetime")
    @TypeConverters({DateTimeConverter.class})
    public Date message_datetime;
}
