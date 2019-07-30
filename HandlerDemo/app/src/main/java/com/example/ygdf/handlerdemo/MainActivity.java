package com.example.ygdf.handlerdemo;


import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageView imgchange;

    int imgids[] = new int []{
            R.drawable.ic_icon_cow,R.drawable.ic_icon_dog,
            R.drawable.ic_icon_duck,R.drawable.ic_icon_fish,
            R.drawable.ic_icon_horse
    };
    int imgstart = 0;

    final Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 0x123){
                imgchange.setImageResource(imgids[imgstart++ % 5]);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgchange = findViewById(R.id.imgchange);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.sendEmptyMessage(0x123);
            }
        },0,200);



    }
}
