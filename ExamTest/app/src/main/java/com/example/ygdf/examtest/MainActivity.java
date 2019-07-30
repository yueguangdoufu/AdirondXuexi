package com.example.ygdf.examtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.ygdf.examtest.helper.PersonAdapter;
import com.example.ygdf.examtest.pojo.Person;

import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;

import static com.example.ygdf.examtest.R.drawable.*;
import static com.example.ygdf.examtest.R.drawable.ic_icon_cow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Person> mData = null;
    private Context mContext;
    private PersonAdapter personAdapter = null;
    private ListView list;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        mData.add(new Person("aaa","jjjj",R.drawable.ic_icon_cow));
        mData.add(new Person("bbb","qqq",R.drawable.ic_icon_dog));
        mData.add(new Person("ccc","nnn",R.drawable.ic_icon_duck));
        personAdapter = new PersonAdapter((LinkedList<Person>) mData,mContext);
        list.setAdapter(personAdapter);
    }

    private void bind(){
        mContext = MainActivity.this;
        list = findViewById(R.id.list);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        mData = new LinkedList<Person>();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,AddActivity.class);
        startActivity(intent);
    }
}
