package com.example.a202sgi_assignment_tms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomAdapter extends ArrayAdapter<Chat> {

    private List<Chat> chatList = new ArrayList<>();
    private Context context;

    public ChatRoomAdapter(Context context, int resource,List<Chat> chatList) {
        super(context, resource, chatList);
        this.context = context;
        this.chatList = chatList;

    }

    public int getItemCount(){
        if(chatList == null || chatList.size() == 0){
            return 0;
        }
        else{
            return chatList.size();
        }
    }

    public int getChatId(int position){
        return chatList.get(position).getChat_id();
    }

    public String getChatName(int position){
        return chatList.get(position).getChat_name();
    }

    public List<Chat> getCurChatList(){
        return this.chatList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View chatView = layoutInflater.inflate(R.layout.chat_item, parent, false);
        TextView mTvChatRoomName = chatView.findViewById(R.id.tvChatRoomName);
        mTvChatRoomName.setText(getChatName(position));

        return chatView;
    }
}

