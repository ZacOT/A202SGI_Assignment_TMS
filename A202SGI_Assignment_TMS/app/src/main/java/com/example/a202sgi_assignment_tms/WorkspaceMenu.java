package com.example.a202sgi_assignment_tms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class WorkspaceMenu extends AppCompatActivity {

    CardView mCvChatRoom, mCvToDoList;
    TextView mCvChatRoomText, mCvToDoListText, mTvWorkspaceInviteCode;
    String workspace_name, workspace_invitecode;
    int workspace_id, curuser_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspacemenu);

        mTvWorkspaceInviteCode = findViewById(R.id.tvWorkspaceInviteCode);
        mCvToDoList = findViewById(R.id.cvToDoList);
        mCvToDoListText = findViewById(R.id.cvToDoListText);
        mCvChatRoom = findViewById(R.id.cvChatRoom);
        mCvChatRoomText = findViewById(R.id.cvChatRoomText);

        mCvToDoListText.setText("TO DO LISTS");
        mCvChatRoomText.setText("CHAT ROOM");

        Bundle extra = getIntent().getExtras();
        workspace_id = extra.getInt("workspace_id");
        curuser_id = extra.getInt("curuser_id");
        workspace_name = extra.getString("workspace_name");
        workspace_invitecode = extra.getString("workspace_invitecode");
        getSupportActionBar().setTitle(workspace_name);

        mTvWorkspaceInviteCode.setText("INVITE CODE:" + workspace_invitecode);

        mCvToDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(WorkspaceMenu.this, ToDoCardActivity.class);
                intent.putExtra("workspace_id", workspace_id);
                intent.putExtra("workspace_name", workspace_name);
                intent.putExtra("curuser_id", curuser_id);
                startActivity(intent);
            }
        });

        mCvChatRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkspaceMenu.this, chatroom.class);
                intent.putExtra("workspace_id", workspace_id);
                intent.putExtra("curuser_id", curuser_id);
                intent.putExtra("workspace_name", workspace_name);
                startActivity(intent);
            }
        });
    }
}