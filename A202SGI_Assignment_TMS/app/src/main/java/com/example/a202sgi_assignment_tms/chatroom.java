package com.example.a202sgi_assignment_tms;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class chatroom extends AppCompatActivity {

    private ListView mLvChatRoom;
    private Button mBtnAddChatRoom;
    private ChatRoomViewModel chatViewModel;
    private ChatRoomAdapter chatAdapter;
    String workspace_name;
    int workspace_id, curuser_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        Bundle extra = getIntent().getExtras();
        workspace_id = extra.getInt("workspace_id");
        curuser_id = extra.getInt("curuser_id");
        workspace_name = extra.getString("workspace_name");

        getSupportActionBar().setTitle(workspace_name);

        initViewModel();
        chatViewModel.getAllChats();

        mLvChatRoom = findViewById(R.id.lvChatRoom);
        mBtnAddChatRoom = findViewById(R.id.btnAddChatRoom);


        mBtnAddChatRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateChatRoom();
            }
        });
    }

    private void showCreateChatRoom(){
        final Dialog dialog = new Dialog(chatroom.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_createchatroom);

        EditText mETChatRoomName = dialog.findViewById(R.id.etEnterChatroomName);
        Button mBtnCreateChatroom = dialog.findViewById(R.id.btnCreateChatroom);
        Button mBtnCancelCreateChatroom = dialog.findViewById(R.id.btnCancelChatroom);

        mBtnCreateChatroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chatRoomName = mETChatRoomName.getText().toString();

                if (chatRoomName.isEmpty()){
                    Toast.makeText(chatroom.this, "Enter A Chat Room Name!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Chat c = new Chat();
                    c.setChat_name(chatRoomName);
                    c.setWorkspace_id(workspace_id);
                    chatViewModel.insertChat(c);
                    dialog.dismiss();
                }
            }
        });
        mBtnCancelCreateChatroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void setUpListViewListener() {
        mLvChatRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int chat_id = chatAdapter.getChatId(i);
                String chat_name = chatAdapter.getChatName(i);
                Intent intent = new Intent(chatroom.this, ChatActivity.class);
                intent.putExtra("chat_id", chat_id);
                intent.putExtra("curuser_id", curuser_id);
                intent.putExtra("chat_name", chat_name);
                startActivity(intent);
                chatAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initViewModel(){
        chatViewModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        Context context = this;

        final Observer<List<Chat>> chatListObserver = new Observer<List<Chat>>(){
            @Override
            public void onChanged(List<Chat> chats){
                if (chats.size() == 0){

                }
                else{
                    chatAdapter = new ChatRoomAdapter(context, android.R.layout.simple_list_item_1, chats);
                    if (!(chatAdapter.getCurChatList().isEmpty())){
                        mLvChatRoom.setAdapter(chatAdapter);
                        setUpListViewListener();
                        chatAdapter.notifyDataSetChanged();
                    }
                }
            }};
        chatViewModel.getAllChats().observe(this, chatListObserver);
    }
}
