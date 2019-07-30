package com.example.ygdf.broadcostdemoapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ygdf.broadcostdemoapplication.helper.MyReceiver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyReceiver receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.MY_BROADCAST");

        registerReceiver(receiver, filter);

        Button btn = findViewById(R.id.sent_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                send();
            }
        });
    }

    private void send() {
        Intent intent = new Intent("android.intent.action.MY_BROADCAST");
        intent.putExtra("msg"," hello receiver.");
        sendBroadcast(intent);
    }
}
