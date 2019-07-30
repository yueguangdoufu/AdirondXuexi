package com.example.ygdf.filew;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ygdf.filew.adapter.MyAdapter;
import com.example.ygdf.filew.pojo.Data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ListViewUpdateActivity extends AppCompatActivity {

    private ListView list_one;
    private MyAdapter myAdapter = null;
    private List<Data> mData = null;
    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_update);

        mContext = ListViewUpdateActivity.this;
        bindViews();
        mData = new LinkedList<Data>();
        myAdapter = new MyAdapter(mContext,(LinkedList<Data>) mData);
        list_one.setAdapter(myAdapter);
    }

    private void bindViews() {
        list_one = findViewById(R.id.list_one);
    }
}
