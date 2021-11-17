package com.example.a202sgi_assignment_tms;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ToDoCardRepository {

    private ToDoCardDao mToDoCardDao;
    private LiveData<List<ToDoCard>> mAllToDoCards;

    ToDoCardRepository(Application application){
        MainDatabase db = MainDatabase.getDatabase(application);
        mToDoCardDao = db.toDoCardDao();
        mAllToDoCards = mToDoCardDao.getAllCards();
    }

    LiveData<List<ToDoCard>> getAllToDoCard(){return mAllToDoCards;}

    public void insert(ToDoCard... toDoCards){new insertAsyncTask(mToDoCardDao).execute(toDoCards);}
    public void delete(int tdcid){new deleteAsyncTask(mToDoCardDao).execute(Integer.valueOf(tdcid));}

    private static class insertAsyncTask extends AsyncTask<ToDoCard, Void, Void> {
        private  ToDoCardDao mAsyncTaskDao;

        insertAsyncTask(ToDoCardDao dao){mAsyncTaskDao = dao;}

        @Override
        protected Void doInBackground(ToDoCard... toDoCards){
            mAsyncTaskDao.insertToDoCard(toDoCards[0]);
            return null;
        }
    }
    private static class deleteAsyncTask extends AsyncTask<Integer, Void, Void>{
        private ToDoCardDao mAsyncTaskDao;

        deleteAsyncTask(ToDoCardDao dao){mAsyncTaskDao = dao;}

        @Override
        protected Void doInBackground(Integer... tdcid){
            mAsyncTaskDao.deleteToDoCard(tdcid[0].intValue());
            return null;
        }
    }
}
