package com.example.a202sgi_assignment_tms;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ToDoCardActivity extends AppCompatActivity {

    private RecyclerView mLvToDoList;
    private Button mBtnAddToDoCard;
    private ToDoCardViewModel todocardViewModel;
    private ToDoCardAdapter todocardAdapter = new ToDoCardAdapter(this);
    int curuser_id;
    String workspace;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todocard);

        Bundle extra = getIntent().getExtras();
        curuser_id = extra.getInt("curuser_id");
        workspace = extra.getString("workspace_name");
        getSupportActionBar().setTitle(workspace);

        mLvToDoList = findViewById(R.id.lvToDoList);
        mBtnAddToDoCard = findViewById(R.id.btnAddCard);

        mBtnAddToDoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateToDoCardDialog();
            }
        });

        mLvToDoList.setLayoutManager(new LinearLayoutManager(this));

        initViewModel();
        todocardViewModel.getAllToDoCard();
        mLvToDoList.setAdapter(todocardAdapter);
        todocardAdapter.setOnItemListenerListener(new ToDoCardAdapter.OnItemListener() {
            @Override
            public void OnItemClickListener(View view, int position) {

            }

            @Override
            public void OnItemLongClickListener(View view, int position) {
                final Dialog dialog = new Dialog(ToDoCardActivity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.activity_deletetodocard);

                Button mBtnDeleteTDC = dialog.findViewById(R.id.btnDeleteTDC);
                Button mBtnCancelDeleteTDC = dialog.findViewById(R.id.btnCancelDeleteTDC);

                mBtnDeleteTDC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        todocardViewModel.deleteToDoCard(todocardAdapter.getCardId(position));
                        todocardAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                mBtnCancelDeleteTDC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }

    void showCreateToDoCardDialog(){
        final Dialog dialog = new Dialog(ToDoCardActivity.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_createtodocard);

        final EditText mETToDoCardTitle = dialog.findViewById(R.id.etToDoCardTitle);
        final EditText mETToDoCardDescription = dialog.findViewById(R.id.etToDoCardDescription);
        DatePicker mDpToDoCardDate = dialog.findViewById(R.id.dpToDoCardDate);
        Button mBtnCreateTodo = dialog.findViewById(R.id.btnCreateTodo);
        Button mBtnCancelTodo = dialog.findViewById(R.id.btnCancelTodo);


        mBtnCreateTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date cardStartDate = new Date();
                ToDoCard tdc = new ToDoCard();
                String tdcTitle = mETToDoCardTitle.getText().toString();
                String tdcDescription = mETToDoCardDescription.getText().toString();


                tdc.todo_title = tdcTitle;
                tdc.todo_description = tdcDescription;
                tdc.todo_startdate = cardStartDate;
                tdc.todo_duedate = getDateFromDatePicker(mDpToDoCardDate);
                tdc.todo_status = "Incomplete";
                tdc.creator_id = curuser_id;

                todocardViewModel.insertToDoCard(tdc);
                todocardAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        mBtnCancelTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void initViewModel(){
        todocardViewModel = new ViewModelProvider(this).get(ToDoCardViewModel.class);

        final Observer<List<ToDoCard>> todocardObserver = new Observer<List<ToDoCard>>() {
            @Override
            public void onChanged(List<ToDoCard> toDoCards) {

                if (toDoCards.size() == 0){

                }else {
                    todocardAdapter.setToDoCardList(toDoCards);
                    todocardAdapter.notifyDataSetChanged();
                }
            }
        };
        todocardViewModel.getAllToDoCard().observe(this, todocardObserver);
    }
    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}