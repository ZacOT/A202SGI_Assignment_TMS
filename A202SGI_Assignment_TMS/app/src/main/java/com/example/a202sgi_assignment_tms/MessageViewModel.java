package com.example.a202sgi_assignment_tms;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {
    private MessageRepository mRepository;
    private UserRepository uRepository;
    private LiveData<List<Message>> mAllMessages;
    private LiveData<List<User>> mAllUser;
    private LiveData<String> mUsername;

    public MessageViewModel(Application application){
        super(application);
        mRepository = new MessageRepository(application);
        uRepository = new UserRepository(application);
        mAllMessages = mRepository.getAllMessages();
        mAllUser = uRepository.getAllUser();
    }

    LiveData<List<Message>> getAllMessages(){return mAllMessages;}
    LiveData<List<User>> getAllUser(){return mAllUser;}



    public void insertMessage(Message message){mRepository.insert(message);}
    public void deleteMessage(Message message){mRepository.delete(message);}
}
