package com.example.a202sgi_assignment_tms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WorkspaceAdapter extends ArrayAdapter<Workspace> {

    private Workspace ws;
    private List<Workspace> workspaceList = new ArrayList<>();
    private List<Workspace> getWorkspaceList = new ArrayList<>();
    private Context context;
    private String text;

    public WorkspaceAdapter(Context context, int resource, List<Workspace> workspaceList, int curUserId){
        super(context, resource, workspaceList);
        this.context = context;
        this.getWorkspaceList = workspaceList;

        for (int i = 0; i < workspaceList.size(); i++){
            //Find if current user is creator of workspace
            if (workspaceList.get(i).creator_id == curUserId){
                this.workspaceList.add(workspaceList.get(i));
            }
            else if(!(workspaceList.get(i).members == curUserId)){
                this.workspaceList.add(workspaceList.get(i));
            }
            else{
                this.workspaceList = new ArrayList<>();
            }}
    }



    public int getWorkspaceId(int position){
        return workspaceList.get(position).workspace_id;
    }

    public List<Workspace> getCurWorkSpaceList(){
        return this.workspaceList;
    }
    public List<Workspace> getWorkspaceList(){
        return getWorkspaceList;
    }

    public String getWorkspaceName(int position){
        return workspaceList.get(position).workspace_name;
    }
    public String getWorkspaceInviteCode(int position){
        return workspaceList.get(position).invite_code;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View workspaceView = layoutInflater.inflate(R.layout.workspace_item, parent, false);
            TextView mTvWorkspaceName = workspaceView.findViewById(R.id.tvWorkspaceName);
            mTvWorkspaceName.setText(getWorkspaceName(position));
            return workspaceView;
    }
}
