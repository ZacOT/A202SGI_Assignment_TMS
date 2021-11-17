package com.example.a202sgi_assignment_tms;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ChatActivity extends AppCompatActivity {

    private MessageViewModel messageViewModel;
    private MessageAdapter messageAdapter;
    private RecyclerView mLvMessages;
    private Button mBtnSend;
    EditText chatBox;
    TextView tvDebug;
    Toolbar tb;
    int curuser_uid, chatid, workspace_id;
    String chat_name;
    Date dateCurrent = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mLvMessages = findViewById(R.id.lvMessages);
        mBtnSend = findViewById(R.id.btnSend);
        chatBox = findViewById(R.id.chatBox);

        Bundle extra = getIntent().getExtras();
        curuser_uid = extra.getInt("curuser_id");
        chat_name = extra.getString("chat_name");

        getSupportActionBar().setTitle(chat_name);


        //Send Message
        mBtnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addItem(view);
            }
        });

        mLvMessages.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter(this);
        initViewModel();
        messageViewModel.getAllMessages();
        messageViewModel.getAllUser();
        mLvMessages.setAdapter(messageAdapter);

    }

    private void addItem(View view){
        String chatBoxText = chatBox.getText().toString();
        Message m = new Message();

        if(!(chatBoxText.isEmpty())){
            m.author_id = curuser_uid;
            m.message_datetime = dateCurrent;
            m.message_content = chatBoxText;
            m.chat_id = chatid;
            messageViewModel.insertMessage(m);
            m = null;
        }
        chatBox.setText("");
    }

    private void initViewModel(){
        messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);

        messageAdapter.setChat_id(chatid);

        final Observer<List<Message>> messageListObserver = new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                if (messages.size()== 0){

                }
                else{
                    messageAdapter.setMessageList(messages);
                }
            }};
        final Observer<List<User>> userListObserver = new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users.size()== 0){

                }
                else{
                    messageAdapter.setUserList(users);
                }
            }
        };
        messageViewModel.getAllMessages().observe(  this,messageListObserver);
        messageViewModel.getAllUser().observe(this, userListObserver);
    }

}
