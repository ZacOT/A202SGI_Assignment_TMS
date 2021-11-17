package com.example.a202sgi_assignment_tms;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chat_table")
public class Chat {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "chat_id")
    @NonNull
    public int chat_id;

    @ColumnInfo(name = "chat_name")
    public String chat_name;

    @ColumnInfo(name = "workspace_id")
    public int workspace_id;

    public int getChat_id() {
        return chat_id;
    }

    public String getChat_name() {
        return chat_name;
    }

    public int getWorkspace_id() {
        return workspace_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public void setChat_name(String chat_name) {
        this.chat_name = chat_name;
    }

    public void setWorkspace_id(int workspace_id) {
        this.workspace_id = workspace_id;
    }
}
