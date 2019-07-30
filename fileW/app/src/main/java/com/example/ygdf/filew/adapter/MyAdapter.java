package com.example.ygdf.filew.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ygdf.filew.R;
//import com.example.ygdf.filew.pojo.Animal;
import com.example.ygdf.filew.pojo.Data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private LinkedList<Data> mData;

    public MyAdapter() {
    }

    public MyAdapter(Context mContext, LinkedList<Data> mData) {
        this.mContext = mContext;
        this.mData = mData;
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
            holder = new ViewHolder();
            holder.img_icon = convertView.findViewById(R.id.img_icon1);
            holder.txt_content = convertView.findViewById(R.id.txt_content);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.img_icon.setImageResource(mData.get(position).getImgId());
        holder.txt_content.setText(mData.get(position).getContent());
//        ImageView img_icom = convertView.findViewById(R.id.img_icon);
//        TextView txt_aName = convertView.findViewById(R.id.txt_aName);
//        TextView txt_aSpeak = convertView.findViewById(R.id.txt_aSpeak);
//        img_icom.setBackgroundResource(mData.get(position).getaIcon());
//        txt_aName.setText(mData.get(position).getaName());
//        txt_aSpeak.setText(mData.get(position).getaSpeak());
        return convertView;
    }

    private class ViewHolder {
        ImageView img_icon;
        TextView txt_content;
    }
}

