package com.example.a202sgi_assignment_tms;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WorkspaceDao {

    @Query("SELECT * FROM workspace_table")
    LiveData<List<Workspace>> getAllWorkspace();

    @Insert
    void insertWorkspace(Workspace workspace);

    @Update
    void updateWorkspace(Workspace workspace);
}
