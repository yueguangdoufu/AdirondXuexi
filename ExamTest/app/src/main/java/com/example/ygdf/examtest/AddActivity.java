package com.example.ygdf.examtest;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ygdf.examtest.helper.MyDBHelper;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit1;
    private EditText edit2;
    private Button btnOk;
    private Button btnBack;
    private MyDBHelper dbHelper;
    private SQLiteDatabase db;
    private Context mContext;

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        bind();
    }

    private void bind(){
        mContext = AddActivity.this;
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        btnOk = findViewById(R.id.btnOk);
        btnBack = findViewById(R.id.btnBack);
        dbHelper = new MyDBHelper(mContext,"my.db",null,1);
        btnOk.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        db = dbHelper.getWritableDatabase();
        switch (v.getId()){
            case R.id.btnOk:
                ContentValues values = new ContentValues();
                values.put("name",edit1.getText().toString());
                values.put("word",edit2.getText().toString());
                db.insert("person",null,values);
                break;
            case R.id.btnBack:
                alert = null;
                builder = new AlertDialog.Builder(mContext);
                alert = builder.setIcon(R.drawable.fish).setTitle("系统提示:").setMessage("是否返回?").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext,"已取消",Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(AddActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }).create();
                alert.show();
                break;

        }
    }
}
