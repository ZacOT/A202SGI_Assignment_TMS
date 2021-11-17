package com.example.a202sgi_assignment_tms;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WorkspaceRepository {

    private WorkspaceDao mWorkspaceDao;
    private LiveData<List<Workspace>> mAllWorkspace;

    WorkspaceRepository(Application application){
        MainDatabase db = MainDatabase.getDatabase(application);
        mWorkspaceDao = db.workspaceDao();
        mAllWorkspace = mWorkspaceDao.getAllWorkspace();
    }

    LiveData<List<Workspace>> getAllWorkspace(){return mAllWorkspace;}

    public void insert (Workspace workspace){new insertAsyncTask(mWorkspaceDao).execute(workspace);}

    private static class insertAsyncTask extends android.os.AsyncTask<Workspace, Void, Void>{
        private WorkspaceDao mAsyncTaskDao;

        insertAsyncTask(WorkspaceDao dao){mAsyncTaskDao = dao;}

        @Override
        protected Void doInBackground(Workspace... workspaces){
            mAsyncTaskDao.insertWorkspace(workspaces[0]);
            return null;
        }
    }
}
