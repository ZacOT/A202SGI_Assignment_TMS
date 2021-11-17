package com.example.a202sgi_assignment_tms;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ChatRepository {

    private ChatDao mChatDao;
    private LiveData<List<Chat>> mAllChats;

    ChatRepository(Application application){
        MainDatabase db = MainDatabase.getDatabase(application);
        mChatDao = db.chatDao();
        mAllChats = mChatDao.getAllChat();
    }

    LiveData<List<Chat>> getAllChats(){return mAllChats;}

    public void insert (Chat chat){new insertAsyncTask(mChatDao).execute(chat);}

    private static class insertAsyncTask extends android.os.AsyncTask<Chat, Void, Void>{
        private ChatDao mAsyncTaskDao;

        insertAsyncTask(ChatDao dao){mAsyncTaskDao = dao;}

        @Override
        protected Void doInBackground(Chat... chats){
            mAsyncTaskDao.insertChat(chats[0]);
            return null;
        }
    }
}
