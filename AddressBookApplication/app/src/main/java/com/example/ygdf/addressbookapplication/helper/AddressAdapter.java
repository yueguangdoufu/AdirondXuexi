package com.example.ygdf.addressbookapplication.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ygdf.addressbookapplication.R;
import com.example.ygdf.addressbookapplication.pojo.Address;

import java.util.LinkedList;

public class AddressAdapter extends BaseAdapter {

    private LinkedList<Address> mData;
    private Context mcontext;

    public AddressAdapter() {
    }

    public AddressAdapter(LinkedList<Address> mData, Context mcontext) {
        this.mData = mData;
        this.mcontext = mcontext;
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

        ViewHodler hodler = null;

        if(hodler == null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.adress, parent, false);
            hodler = new ViewHodler();
            hodler.id = convertView.findViewById(R.id.addressId);
            hodler.head = convertView.findViewById(R.id.addressHead);
            hodler.name = convertView.findViewById(R.id.addressName);
            hodler.phone = convertView.findViewById(R.id.addressPhone);
            convertView.setTag(hodler);
        }else {
            hodler = (ViewHodler) convertView.getTag();
        }
        hodler.id.setText(String.valueOf(mData.get(position).getId()));
        //hodler.head.setBackgroundResource(mData.get(position).getHead());
        hodler.name.setText(mData.get(position).getName());
        hodler.phone.setText(String.valueOf(mData.get(position).getPhone()));
        return convertView;
    }

    public class ViewHodler{
        ImageView head;
        TextView name;
        TextView phone;
        TextView id;
    }

    public void add(Address data){
        if (mData == null){
            mData = new LinkedList<>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }

    public void remove(Address data) {
        if(mData != null) {
            mData.remove(data);
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if(mData != null) {
            mData.remove(position);
        }
        notifyDataSetChanged();
    }
}
