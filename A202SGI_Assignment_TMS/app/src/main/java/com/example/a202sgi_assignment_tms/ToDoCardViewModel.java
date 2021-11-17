package com.example.a202sgi_assignment_tms;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ToDoCardViewModel extends AndroidViewModel {

    private ToDoCardRepository tdcRepository;
    private LiveData<List<ToDoCard>> mAllToDoCards;

    public ToDoCardViewModel(Application application){
        super(application);

        tdcRepository = new ToDoCardRepository(application);
        mAllToDoCards = tdcRepository.getAllToDoCard();
    }

    LiveData<List<ToDoCard>> getAllToDoCard(){return mAllToDoCards;}

    public void insertToDoCard(ToDoCard toDoCard){tdcRepository.insert(toDoCard);}

    public void deleteToDoCard(int tdcid){tdcRepository.delete(tdcid);}

}
