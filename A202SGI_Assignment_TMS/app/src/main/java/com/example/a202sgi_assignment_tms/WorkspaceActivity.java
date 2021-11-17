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

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class WorkspaceActivity extends AppCompatActivity {

    private WorkspaceAdapter workspaceAdapter;
    private WorkspaceViewModel workspaceViewModel;
    private ListView mLvWorkspace;
    private Button mBtnCreateWorkspace, mBtnJoinWorkspace;
    int curuser_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspace);


        Bundle extra = getIntent().getExtras();
        curuser_id = extra.getInt("curuser_id");

        getSupportActionBar().setTitle("Workspace List");

        mLvWorkspace = findViewById(R.id.lvWorkspace);
        mBtnCreateWorkspace = findViewById(R.id.btnCreateWorkspace);
        mBtnJoinWorkspace = findViewById(R.id.btnJoinWorkspace);

        initViewModel();
        workspaceViewModel.getAllWorkspace();

        mBtnCreateWorkspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateWorkspaceDialog();
            }
        });

        mBtnJoinWorkspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInviteCodeDialog(curuser_id);
            }
        });

    }

    void showCreateWorkspaceDialog(){
        final Dialog dialog = new Dialog(WorkspaceActivity.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_createworkspace);

        final EditText etWorkspaceName = dialog.findViewById(R.id.etEnterWorkspaceName);
        Button mBtnCreate = dialog.findViewById(R.id.btnCreate);
        Button mBtnCancel = dialog.findViewById(R.id.btnCancel);

        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Workspace w = new Workspace();
                Chat c = new Chat();
                String workspaceName = etWorkspaceName.getText().toString();

                w.creator_id = curuser_id;
                w.workspace_name = workspaceName;
                w.invite_code = createInviteCode();

                workspaceViewModel.insertWorkspace(w);

                dialog.dismiss();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    void showInviteCodeDialog(int curuser_id){
        final Dialog dialog = new Dialog(WorkspaceActivity.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_invitecode);

        EditText mETEnterInviteCode = dialog.findViewById(R.id.etEnterInviteCode);
        Button mBtnJoinInviteCode = dialog.findViewById(R.id.btnJoinInviteCode);
        Button mBtnCancelInviteCode = dialog.findViewById(R.id.btnCancelInviteCode);


        mBtnJoinInviteCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Workspace> ws = workspaceAdapter.getWorkspaceList();

                for (int i = 0; i < ws.size(); i++){
                    if (ws.get(i).invite_code.equals(mETEnterInviteCode.getText().toString()))
                    {
                        ws.get(i).members = curuser_id;
                    }
                }
                dialog.dismiss();
            }
        });
        mBtnCancelInviteCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void setUpListViewListener() {
        mLvWorkspace.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String workspaceName = workspaceAdapter.getWorkspaceName(i);
                int workspace_id = workspaceAdapter.getWorkspaceId(i);
                String workspace_invitecode = workspaceAdapter.getWorkspaceInviteCode(i);

                Intent intent = new Intent(WorkspaceActivity.this, WorkspaceMenu.class);
                intent.putExtra("workspace_id", workspace_id);
                intent.putExtra("curuser_id", curuser_id);
                intent.putExtra("workspace_name", workspaceName);
                intent.putExtra("workspace_invitecode", workspace_invitecode);
                startActivity(intent);

                workspaceAdapter.notifyDataSetChanged();
            }
        });
    }

    public String createInviteCode(){
        char[] cArray = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new SecureRandom();

        for (int i = 0; i < 8; i++){
            char c = cArray[random.nextInt(cArray.length)];
            stringBuilder.append(c);
        }

        String generateCode = stringBuilder.toString();
        return generateCode;
    }

    private void initViewModel(){
        workspaceViewModel = new ViewModelProvider(this).get(WorkspaceViewModel.class);
        Context context = this;

        final Observer<List<Workspace>> workspaceListObserver = new Observer<List<Workspace>>(){
            @Override
            public void onChanged(List<Workspace> workspaces) {

                workspaceAdapter = new WorkspaceAdapter(context, android.R.layout.simple_list_item_1, workspaces, curuser_id);
                if (!(workspaceAdapter.getCurWorkSpaceList().isEmpty())){
                    mLvWorkspace.setAdapter(workspaceAdapter);
                    setUpListViewListener();
                }

            }};
        workspaceViewModel.getAllWorkspace().observe(this, workspaceListObserver);

    }

}