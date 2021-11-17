package com.example.a202sgi_assignment_tms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mBtnRegister, mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mBtnRegister = findViewById(R.id.btnRegister);
        mBtnLogin = findViewById(R.id.btnLogin);
        mEmail = findViewById(R.id.loginEmail);
        mPassword = findViewById(R.id.loginPassword);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(login.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                }
                else{

                    MainDatabase maindb = MainDatabase.getDatabase(getApplicationContext());
                    final UserDao userDao = maindb.userDao();
                    final UserRepository userRepository = new UserRepository(getApplication()) ;

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            User user = userDao.login(email, password);
                            if (user == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        int curUser = user.getUser_id();
                                        openWorkspace(curUser);
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRegisterPage();
            }
        });
    }

    public void openRegisterPage(){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }
    public void openWorkspace(int curUser){
        Intent intent = new Intent(this, WorkspaceActivity.class);
        intent.putExtra("curuser_id", curUser);
        startActivity(intent);
    }

}