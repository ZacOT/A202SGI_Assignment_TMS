package com.example.a202sgi_assignment_tms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {
    private Context context;
    private List<Chat> chatList = new ArrayList<>();
    private List<Message> messageList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private int curChat_id;

    public MessageAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.message_item,parent,false);
        return new MessageHolder(view);
    }

    public void setChat_id(int chat_id){
        this.curChat_id = chat_id;
        notifyDataSetChanged();
    }

    public void setMessageList(List<Message> messageList){

        this.messageList = new ArrayList<>();

        for(int i = 0; i<messageList.size(); i++){
            //Check Message chat_id = current chat_id
            if(messageList.get(i).chat_id == curChat_id){
                this.messageList.add(messageList.get(i));
            }
        }

        notifyDataSetChanged();
    }
    public void setUserList(List<User> userList){
        this.userList = userList;
        notifyDataSetChanged();
    }



    public String getUsername(int authorid){
        String username;
        for (int i = 0; i<userList.size(); i++){
            if(userList.get(i).user_id == authorid){
                return userList.get(i).user_name;
            }
        }
        return "Unknown";
    }

    @Override
    public int getItemCount(){
        if (messageList==null || messageList.size()==0) {
            return 0;
        }
        return messageList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dateCreated = (messageList.get(position).message_datetime);
        String textDateCreated = df.format(dateCreated);

        holder.mTvMessage.setText(messageList.get(position).message_content);
        holder.mTvDateTime.setText(textDateCreated);
        holder.mTvAuthor.setText(getUsername(messageList.get(position).author_id));
    }

    public class MessageHolder extends RecyclerView.ViewHolder{
        TextView mTvMessage;
        TextView mTvDateTime;
        TextView mTvAuthor;

        public MessageHolder(@NonNull View itemView){
            super(itemView);

            mTvMessage = itemView.findViewById(R.id.tvMessageContent);
            mTvDateTime = itemView.findViewById(R.id.tvDateTime);
            mTvAuthor = itemView.findViewById(R.id.tvAuthor);
        }
    }
}
