package com.example.a202sgi_assignment_tms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {

    EditText mEmail;
    EditText mUsername;
    EditText mPassword;
    EditText mConfirmPassword;
    Button mBtnRegister;
    Button mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmail = findViewById(R.id.registerEmail);
        mUsername = findViewById(R.id.registerName);
        mPassword = findViewById(R.id.registerPassword);
        mConfirmPassword = findViewById(R.id.registerConfirmPassword);
        mBtnRegister = findViewById(R.id.btnRegisterRg);
        mBtnBack = findViewById(R.id.btnBack);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User user = new User();

                String email = mEmail.getText().toString();
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                String confirmpassword = mConfirmPassword.getText().toString();

                if(email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()){
                    Toast.makeText(register.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                }
                else if(checkNotMatchPassword(password, confirmpassword)){
                    Toast.makeText(register.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                }
                else{
                    MainDatabase maindb = MainDatabase.getDatabase(getApplicationContext());
                    final UserDao userDao = maindb.userDao();

                    user.setUser_email(email);
                    user.setUser_name(username);
                    user.setPassword(password);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.registerUser(user);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
                }
            }
        });

        mBtnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openLoginPage();
            }
        });

    }
    public void openLoginPage(){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }


    public Boolean checkNotMatchPassword (String password, String cfmPassword){
        if (password != cfmPassword){
            return false;
        }
        else{
            return true;
        }
    }
}