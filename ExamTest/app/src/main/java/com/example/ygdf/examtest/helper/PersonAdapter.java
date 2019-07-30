package com.example.ygdf.examtest.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ygdf.examtest.R;
import com.example.ygdf.examtest.pojo.Person;

import java.util.LinkedList;

public class PersonAdapter extends BaseAdapter{


    private LinkedList<Person> mData;
    private Context mContext;

    public PersonAdapter(LinkedList<Person> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.list_pre,parent,false);
        ImageView img = convertView.findViewById(R.id.img);
        TextView txt1 = convertView.findViewById(R.id.txt1);
        TextView txt2 = convertView.findViewById(R.id.txt2);
        img.setBackgroundResource(mData.get(position).getImg());
        txt1.setText(mData.get(position).getName());
        txt2.setText(mData.get(position).getWrod());
        return convertView;
    }
}
