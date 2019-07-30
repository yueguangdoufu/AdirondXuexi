package com.example.ygdf.filew;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ygdf.filew.adapter.MyAdapter;
import com.example.ygdf.filew.pojo.Animal;

import java.util.LinkedList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private List<Animal> mData = null;
    private Context mContext;
    private MyAdapter mAdapter = null;
    private ListView list_animal;
    private LinearLayout ly_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mContext = ListViewActivity.this;
        list_animal = findViewById(R.id.list_animal);

        final LayoutInflater inflater = LayoutInflater.from(this);
        View headView = inflater.inflate(R.layout.view_header,null,false);
        View footView = inflater.inflate(R.layout.view_footer,null,false);

        mData = new LinkedList<Animal>();
        mData.add(new Animal("狗说", "你是狗么?", R.mipmap.ic_icon_dog));
        mData.add(new Animal("牛说", "你是牛么?", R.mipmap.ic_icon_cow));
        mData.add(new Animal("鸭说", "你是鸭么?", R.mipmap.ic_icon_duck));
        mData.add(new Animal("鱼说", "你是鱼么?", R.mipmap.ic_icon_fish));
        mData.add(new Animal("马说", "你是马么?", R.mipmap.ic_icon_horse));
        mAdapter = new MyAdapter(mContext,(LinkedList<Animal>)mData);

        list_animal.addHeaderView(headView);
        list_animal.addFooterView(footView);

        list_animal.setAdapter(mAdapter);
        list_animal.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mContext,"你点击了第" + position + "项", Toast.LENGTH_SHORT).show();
    }
}
