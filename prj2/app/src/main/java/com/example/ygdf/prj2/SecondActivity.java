package com.example.ygdf.prj2;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends Activity {
    private String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(TAG,"*******OnCreate********");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnSec = findViewById(R.id.btnSec);

        btnSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,FirstActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG,"***********onStart***********");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG,"***********onStop***********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG,"***********onPause***********");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG,"***********onResume***********");
    }

}
