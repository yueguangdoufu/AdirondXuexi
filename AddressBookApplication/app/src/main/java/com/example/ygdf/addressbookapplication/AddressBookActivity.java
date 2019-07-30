package com.example.ygdf.addressbookapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ygdf.addressbookapplication.helper.AddressAdapter;
import com.example.ygdf.addressbookapplication.helper.MyDBOpenHelper;
import com.example.ygdf.addressbookapplication.pojo.Address;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AddressBookActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnAdd;
    private ListView list;
    private AddressAdapter mAdapter = null;
    private List<Address> mData = null;
    private Context mContext = null;
    private MyDBOpenHelper myDBHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);

        bindViews();
        myDBHelper = new MyDBOpenHelper(mContext,"my.db",null,1);
        mData = new LinkedList<Address>();
        getData();
        mAdapter = new AddressAdapter((LinkedList<Address>) mData, mContext);
        list.setAdapter(mAdapter);

    }

    private void bindViews(){
        list = findViewById(R.id.addressList);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        mContext = AddressBookActivity.this;
    }

    private void getData(){
        db = myDBHelper.getReadableDatabase();
        Cursor cursor = db.query("address_book", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int phone = cursor.getInt(cursor.getColumnIndex("phone"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                Address address = new Address();
                address.setId(id);
                address.setPhone(phone);
                address.setName(name);
                mData.add(address);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(AddressBookActivity.this,AddActivity.class);
        startActivity(intent);
    }
}
