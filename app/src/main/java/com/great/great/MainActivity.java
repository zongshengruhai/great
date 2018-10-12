package com.great.great;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mTest;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getBaseContext();

        mTest = findViewById(R.id.test);
        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor wUserData = getSharedPreferences("UserData",MODE_PRIVATE).edit();
                wUserData.putInt("i_userId",-1);
                wUserData.putInt("i_coId",0);
                wUserData.commit();

                startActivity(new Intent(mContext,LoginActivity.class));

            }
        });


//        SharedPreferences rUserData = getSharedPreferences("UserData", 0);
//        iUserId = rUserData.getInt("i_userId",-1);
//        if (iUserId == -1){
////            Intent intent = new Intent(this,LoginActivity.class);
//            startActivity(new Intent(this,LoginActivity.class));
//        }

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

    Handler mHandler = new Handler();
    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(mRunnable,100);



        }
    };

}
