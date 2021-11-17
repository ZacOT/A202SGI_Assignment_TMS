package com.example.a202sgi_assignment_tms;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WorkspaceViewModel extends AndroidViewModel {

    private WorkspaceRepository wRepository;
    private LiveData<List<Workspace>> mAllWorkspace;

    public WorkspaceViewModel(Application application){
        super(application);
        wRepository = new WorkspaceRepository(application);
        mAllWorkspace = wRepository.getAllWorkspace();
    }

    LiveData<List<Workspace>> getAllWorkspace(){return mAllWorkspace;}

    public void insertWorkspace(Workspace workspace){wRepository.insert(workspace);}
}
