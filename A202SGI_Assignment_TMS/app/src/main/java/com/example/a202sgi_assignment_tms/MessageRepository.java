package com.example.a202sgi_assignment_tms;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MessageRepository {

    private MessageDao mMessageDao;
    private LiveData<List<Message>> mAllMessages;

    MessageRepository(Application application){
        MainDatabase db = MainDatabase.getDatabase(application);
        mMessageDao = db.messageDao();
        mAllMessages = mMessageDao.getAllMessages();
    }

    public void insert (Message message){new insertAsyncTask(mMessageDao).execute(message);}
    public void delete (Message message){new deleteAsyncTask(mMessageDao).execute(message);}


    LiveData<List<Message>> getAllMessages(){
        return mAllMessages;
    }

    private static class deleteAsyncTask extends android.os.AsyncTask<Message, Void, Void>{
        private MessageDao mAsyncTaskDao;

        deleteAsyncTask(MessageDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Message... messages){
            mAsyncTaskDao.deleteMessage(messages[0]);
            return null;
        }
    }

    private static class insertAsyncTask extends android.os.AsyncTask<Message, Void, Void>{
        private MessageDao mAsyncTaskDao;


        insertAsyncTask(MessageDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Message... messages){
            mAsyncTaskDao.insertMessage(messages[0]);
            return null;
        }
    }


}
