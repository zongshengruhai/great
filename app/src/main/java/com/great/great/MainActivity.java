package com.great.great;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity{

    public int MARK = 0;

    private TextView[] bMenuText;
    private ImageView[] bMenuImage;
    private LinearLayout[] bLinear;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getBaseContext();

//        SharedPreferences rUserData = getSharedPreferences("UserData", 0);
//        iUserId = rUserData.getInt("i_userId",-1);
//        if (iUserId == -1){
////            Intent intent = new Intent(this,LoginActivity.class);
//            startActivity(new Intent(this,LoginActivity.class));
//        }

        initBottomMenu();

    }

    /**
     * 初始化底部导航栏
     */
    private void initBottomMenu() {

        bMenuText = new TextView[4];
        bMenuText[0] = findViewById(R.id.menu_tag_1_text);
        bMenuText[0].setTextColor(getResources().getColor(R.color.buttonSelect));
        bMenuText[1] = findViewById(R.id.menu_tag_2_text);
        bMenuText[1].setTextColor(getResources().getColor(R.color.buttonUnSelect));
        bMenuText[2] = findViewById(R.id.menu_tag_3_text);
        bMenuText[2].setTextColor(getResources().getColor(R.color.buttonUnSelect));
        bMenuText[3] = findViewById(R.id.menu_tag_4_text);
        bMenuText[3].setTextColor(getResources().getColor(R.color.buttonUnSelect));

        bMenuImage = new ImageView[4];
        bMenuImage[0] = findViewById(R.id.menu_tag_1_image);
        setImageColor(bMenuImage[0],getResources().getColor(R.color.buttonSelect));
        bMenuImage[1] = findViewById(R.id.menu_tag_2_image);
        setImageColor(bMenuImage[1],getResources().getColor(R.color.buttonUnSelect));
        bMenuImage[2] = findViewById(R.id.menu_tag_3_image);
        setImageColor(bMenuImage[2],getResources().getColor(R.color.buttonUnSelect));
        bMenuImage[3] = findViewById(R.id.menu_tag_4_image);
        setImageColor(bMenuImage[3],getResources().getColor(R.color.buttonUnSelect));

        bLinear = new LinearLayout[4];
        bLinear[0] = findViewById(R.id.menu_tag_1);
        bLinear[1] = findViewById(R.id.menu_tag_2);
        bLinear[2] = findViewById(R.id.menu_tag_3);
        bLinear[3] = findViewById(R.id.menu_tag_4);

    }

    /**
     * 改变ImageView颜色的setTintList方法封装
     * @param image need change of ImageView
     * @param color change of Color
     */
    private void setImageColor(ImageView image,int color){

        Drawable[] drawables = new Drawable[2];
        drawables[0] = image.getDrawable();
        drawables[1] = DrawableCompat.wrap(drawables[0]);
        DrawableCompat.setTintList(drawables[1],ColorStateList.valueOf(color));
        image.setImageDrawable(drawables[1]);

    }

    public void LayoutOnClick(View v){
        setImageColor(bMenuImage[3],getResources().getColor(R.color.buttonSelect));
        resetBottomMenu();
        switch (v.getId()){
            case R.id.menu_tag_1:
                MARK = 0;
                bMenuText[0].setTextColor(getResources().getColor(R.color.buttonSelect));
                setImageColor(bMenuImage[0],getResources().getColor(R.color.buttonSelect));
                break;
            case R.id.menu_tag_2:
                MARK = 1;
                bMenuText[1].setTextColor(getResources().getColor(R.color.buttonSelect));
                setImageColor(bMenuImage[1],getResources().getColor(R.color.buttonSelect));
                break;
            case R.id.menu_tag_3:
                MARK = 2;
                bMenuText[2].setTextColor(getResources().getColor(R.color.buttonSelect));
                setImageColor(bMenuImage[2],getResources().getColor(R.color.buttonSelect));
                break;
            case R.id.menu_tag_4:
                MARK = 3;
                bMenuText[3].setTextColor(getResources().getColor(R.color.buttonSelect));
                setImageColor(bMenuImage[3],getResources().getColor(R.color.buttonSelect));
                break;
        }
    }

    private void resetBottomMenu(){
        for (int i = 0; i < 4 ; i++) {
            bMenuText[i].setTextColor(getResources().getColor(R.color.buttonUnSelect));
            setImageColor(bMenuImage[i],getResources().getColor(R.color.buttonUnSelect));
        }
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