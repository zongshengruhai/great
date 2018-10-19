package com.great.great;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.great.great.HttpManager.HttpDataFunc;
import com.great.great.HttpManager.HttpUrlFunc;
import com.jaeger.library.StatusBarUtil;


public class LoginActivity extends AppCompatActivity {

    private EditText loginUserName;
    private EditText loginUserPass;
    private Button loginUserBt;

    private Context mContext;

    private String loginTag;
    private boolean _isLogin = false;

    private String urlPath = "http://119.23.255.140/php_data/uiinterface.php?reqType=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary),0);
//        StatusBarUtil.setTranslucent(LoginActivity.this,55);

        loginUserName = findViewById(R.id.LoginUsrName);
        loginUserPass = findViewById(R.id.LoginUsrPass);
        loginUserBt = findViewById(R.id.LoginButton);

        mContext = getBaseContext();

        initEdit();

    }

    @Override
    public void onResume(){
        super.onResume();
        mHandler.post(mRunnable);
    }
    @Override
    public void onPause(){
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
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

       new Thread(new Runnable() {
            @Override
            public void run() {

                //基础判断
                String userName = loginUserName.getText().toString();
                String userPass = loginUserPass.getText().toString();

                if (userName.equals("")){
                    loginTag = "没有输入用户名";
                    _isLogin = true;
                    return;
                }

                if (userPass.equals("")){
                    loginTag = "没有输入密码";
                    _isLogin = true;
                    return;
                }

                //登录
                String HttpContent;

                HttpContent = HttpUrlFunc.HttpUrlGET(urlPath + "Userlogin&username=" + userName + "&passwd=" + userPass + "&time=" + HttpDataFunc.getTimestamp());
                if (HttpContent == null){
                    loginTag = "服务器链接失败";
                    _isLogin = true;
                    return;
                }

                int userid = HttpDataFunc.StringToJSONObject(HttpContent).optInt("userid");
                String err = HttpDataFunc.StringToJSONObject(HttpContent).optString("err");

                if (userid == -1){
                    loginTag = "登录失败,"+err;
                    _isLogin = true;
                    return;
                }

                int coid = HttpDataFunc.StringToJSONObject(HttpContent).optInt("coid");

                loginTag = "登录成功";
                _isLogin = true;

                //用户数据保存
                SharedPreferences.Editor wUserData = getSharedPreferences("UserData",MODE_PRIVATE).edit();
                wUserData.putInt("i_userId",userid);
                wUserData.putInt("i_coId",coid);
                wUserData.commit();

                //切换页面
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(mContext,MainActivity.class);
                startActivity( intent);

            }
        }).start();

    }

    Handler mHandler = new Handler();
    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this,100);

            if (_isLogin){
                Toast.makeText(mContext,loginTag,Toast.LENGTH_SHORT).show();
                loginTag = "";
                _isLogin = false;
            }

        }
    };

}
