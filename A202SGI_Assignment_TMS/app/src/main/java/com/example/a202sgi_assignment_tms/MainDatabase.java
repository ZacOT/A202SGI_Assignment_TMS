package com.example.a202sgi_assignment_tms;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class, Chat.class, Message.class, ToDoCard.class, Workspace.class}, version = 1, exportSchema = false)
@TypeConverters({ArrayStringConverter.class, DateTimeConverter.class})
public abstract class MainDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ChatDao chatDao();
    public abstract ToDoCardDao toDoCardDao();
    public abstract WorkspaceDao workspaceDao();
    public abstract MessageDao messageDao();

    public static MainDatabase INSTANCE;
    public static MainDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (MainDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MainDatabase.class, "main_database")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }

        return INSTANCE;
    }
}