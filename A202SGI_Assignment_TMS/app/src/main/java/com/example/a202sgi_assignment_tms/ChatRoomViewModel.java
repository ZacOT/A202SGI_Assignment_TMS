package com.example.a202sgi_assignment_tms;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ChatRoomViewModel extends AndroidViewModel {
    private ChatRepository cRepository;
    private LiveData<List<Chat>> mAllChats;

    public ChatRoomViewModel(Application application){
        super(application);
        cRepository = new ChatRepository(application);
        mAllChats = cRepository.getAllChats();
    }

    LiveData<List<Chat>> getAllChats(){return mAllChats;}

    public void insertChat(Chat chat){cRepository.insert(chat);}
}
