package com.example.a202sgi_assignment_tms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToDoCardAdapter extends RecyclerView.Adapter<ToDoCardAdapter.ToDoCardHolder> {

    private OnItemListener onItemListener;
    private Context context;
    private List<ToDoCard> toDoCardList = new ArrayList<>();

    public ToDoCardAdapter(Context context){this.context = context;}

    public void setToDoCardList(List<ToDoCard> tdcList){
        this.toDoCardList = tdcList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ToDoCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.todocard_item, parent, false);

        view.setOnClickListener(new RV_ItemListener());
        view.setOnLongClickListener(new RV_ItemListener());
        return new ToDoCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoCardAdapter.ToDoCardHolder holder, int position) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateStart = (toDoCardList.get(position).todo_startdate);
        Date dateDue = (toDoCardList.get(position).todo_duedate);
        long diff = Math.abs(dateStart.getTime() - dateDue.getTime());
        long dayDiff = diff / (24 * 60 * 60 * 1000);

        String textDateStart = df.format(dateStart);
        String textDateDue = df.format(dateDue);

        holder.mTvToDoCardTitle.setText("Title: " + toDoCardList.get(position).todo_title);
        holder.mTvToDoCardDescription.setText("Description:" + toDoCardList.get(position).todo_description);
        holder.mTvToDoCardDate.setText(textDateStart + " - " +textDateDue + "[" + dayDiff + " Days Left] iD: " + toDoCardList.get(position).todo_id);
        holder.itemView.setId(position);
    }

    @Override
    public int getItemCount() {
        return toDoCardList.size();
    }
    public int getCardId(int position){return toDoCardList.get(position).todo_id;}

    public class ToDoCardHolder extends  RecyclerView.ViewHolder{
        TextView mTvToDoCardTitle;
        TextView mTvToDoCardDate;
        TextView mTvToDoCardDescription;
        TextView mTvToDoCardStatus;

        public ToDoCardHolder(@NonNull View itemView){
            super(itemView);

            mTvToDoCardTitle = itemView.findViewById(R.id.tvToDoCardTitle);
            mTvToDoCardDate = itemView.findViewById(R.id.tvToDoCardDate);
            mTvToDoCardDescription = itemView.findViewById(R.id.tvToDoCardDescription);
            mTvToDoCardStatus = itemView.findViewById(R.id.tvToDoCardStatus);
        }
    }

    class RV_ItemListener implements View.OnClickListener, View.OnLongClickListener{

        @Override
        public void onClick(View view) {
            if (onItemListener != null){
                onItemListener.OnItemClickListener(view, view.getId());
            }
        }
        @Override
        public boolean onLongClick(View view) {
            if (onItemListener != null){
                onItemListener.OnItemLongClickListener(view,view.getId());
            }
            return true;
        }
    }

    public  interface OnItemListener{
        void OnItemClickListener(View view, int position);
        void OnItemLongClickListener(View view, int position);
    }

    public void setOnItemListenerListener(OnItemListener listener){
        this.onItemListener = listener;
    }
}
