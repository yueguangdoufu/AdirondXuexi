package com.example.ygdf.handlerdemo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HandlerActivity extends AppCompatActivity {

    static final String UPPER_NUM = "upper";
    EditText etNum;
    CalThread calThread;

    class CalThread extends Thread{
        public Handler mHandler;

        public void run(){
            Looper.prepare();
            mHandler = new Handler(){
              @Override
              public void  handleMessage(Message msg){
                  if(msg.what == 0x123){
                      int upper = msg.getData().getInt(UPPER_NUM);
                      List<Integer> nums = new ArrayList<Integer>();
                      outer:
                      for(int i = 2 ;i <= upper; i++){
                          for(int j = 2 ; j <= Math.sqrt(i);j++){
                              if(i != 2 && i % j ==0){
                                  continue outer;
                              }
                          }
                          nums.add(i);
                      }
                      Toast.makeText(CalPrime.this , nums.toString(), Toast.LENGTH_LONG).show();
                  }
              }
            };
            Looper.loop();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        etNum = findViewById(R.id.etNum);
        calThread = new CalThread();
        calThread.start();
    }

    public void cal(View source){
        Message msg = new Message();
        msg.what = 0x123;
        Bundle bundle = new Bundle();
        bundle.putInt(UPPER_NUM,Integer.parseInt(etNum.getText().toString()));
        msg.setData(bundle);
        calThread.mHandler.sendMessage(msg);
    }
}
