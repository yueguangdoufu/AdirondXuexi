package com.example.ygdf.contentresolverapplication;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView smsV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smsV = findViewById(R.id.txt1);
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://sms/");

        resolver.registerContentObserver(uri,true,new MyObserver(new Handler()));

    }

    private class MyObserver extends ContentObserver{

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyObserver(Handler handler) {
            super(handler);
        }

        @SuppressLint("WrongConstant")
        public void onChange(boolean selfChange){
            super.onChange(selfChange);
            Toast.makeText(MainActivity.this,"数据库的内容变化了",1).show();
            Uri uri =Uri.parse("content://sms/");
            ContentResolver resolver = getContentResolver();
            Cursor cursor = resolver.query(uri,new String[]{"address","date","type","body"},null,null,null);
            cursor.moveToFirst();
            String address = cursor.getString(0);
            String body =cursor.getString(3);
            smsV.setText("短信内容:"+body+"\n"+"短信地址:"+address);
            cursor.close();
        }
    }
}
