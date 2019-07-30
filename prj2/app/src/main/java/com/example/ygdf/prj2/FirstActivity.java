package com.example.ygdf.prj2;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends Activity implements View.OnClickListener{
    private String TAG = "FirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(TAG,"********onCreate********");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button btnFir = findViewById(R.id.btnFIr);
        Button btnc2 = findViewById(R.id.btnc2);
        Button btnc3 = findViewById(R.id.btnc3);
        Button btnc4 = findViewById(R.id.btnc4);

        btnc2.setOnClickListener(this);
        btnc3.setOnClickListener(new MyOnClickListener());
        btnc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this,"通过匿名内部类实现Onclick事件监听器",Toast.LENGTH_LONG).show();
            }
        });

        btnFir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
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

    public void onButtonClick(View view){
        Toast.makeText(this,"通过xmlOnclick属性注册事件监听器",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(this,"通过主类实现Onclick事件监听器",Toast.LENGTH_LONG).show();
    }

    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Toast.makeText(FirstActivity.this,"通过内部类实现Onclick事件监听器",Toast.LENGTH_LONG).show();
        }
    }
}
