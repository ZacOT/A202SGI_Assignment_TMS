package com.example.a202sgi_assignment_tms;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;

@Entity(tableName = "workspace_table")
public class Workspace {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "workspace_id")
    @NonNull
    int workspace_id;

    @ColumnInfo(name = "workspace_name")
    String workspace_name;

    @ColumnInfo(name = "creator_id")
    int creator_id;

    @ColumnInfo(name = "member")
    int members;

    @ColumnInfo(name = "invite_code")
    String invite_code;

}
