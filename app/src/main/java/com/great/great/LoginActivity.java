package com.great.great;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.great.great.HttpManager.HttpDataFunc;

public class LoginActivity extends AppCompatActivity {

    private EditText loginUserName;
    private EditText loginUserPass;
    private Button loginUserBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUserName = findViewById(R.id.LoginUsrName);
        loginUserPass = findViewById(R.id.LoginUsrPass);
        loginUserBt = findViewById(R.id.LoginButton);

        initEdit();

    }

    //输入初始化
    private void initEdit(){

        //登录
        loginUserBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });





    }

    //登录
    private void login(){

    }

}
