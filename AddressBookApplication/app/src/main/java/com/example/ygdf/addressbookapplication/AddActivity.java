package com.example.ygdf.addressbookapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ygdf.addressbookapplication.helper.MyDBOpenHelper;
import com.example.ygdf.addressbookapplication.pojo.Address;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    private MyDBOpenHelper myDBOpenHelper;
    private SQLiteDatabase db;
    private EditText id;
    private EditText name;
    private EditText phone;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnBack;
    private Button btnUpdata;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        bindViews();
    }

    private void bindViews(){
        mContext = AddActivity.this;
        id = findViewById(R.id.editUpdId);
        name = findViewById(R.id.editUpdName);
        phone = findViewById(R.id.editUpdPhone);
        btnAdd = findViewById(R.id.btnAddOk);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdata = findViewById(R.id.btnUpdata);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnUpdata.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        myDBOpenHelper = new MyDBOpenHelper(mContext,"my.db",null,1);
    }


    @Override
    public void onClick(View v) {
        int key = v.getId();
        switch (key){
            case R.id.btnUpdata:
                Address a = new Address();
                int aid = 0;
                if (id.getText().equals("")||id.getText().equals(" ")||id.getText() == null){
                    Toast.makeText(mContext,"请要修改用户的输入id",Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    aid = Integer.parseInt(String.valueOf(id.getText()));
                    a.setId(aid);
                    if(phone.getText().equals("")||phone.getText().equals(" ")||phone.getText() == null){
                        a.setPhone(find(aid).getPhone());
                    }else {
                        int aphone = Integer.parseInt(String.valueOf(phone.getText()));
                        a.setPhone(aphone);
                    }
                    if(name.getText().equals("")||name.getText().equals(" ")||name.getText() == null){
                        a.setName(find(aid).getName());
                    }else {
                        String aname = String.valueOf(name.getText());
                        a.setName(aname);
                    }
                }

                update(a);
                Toast.makeText(mContext,"修改成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnAddOk:
                Address a1 = new Address();

                if(name.getText().equals(" ")||name.getText().equals("")||name.getText() == null) {
                    Toast.makeText(mContext, "请输入添加的姓名", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    String aname1 = String.valueOf(name.getText());
                    a1.setName(aname1);
                }
                if(phone.getText().equals("")||phone.getText().equals(" ")||phone.getText() == null){
                    Toast.makeText(mContext,"请输入联系人电话",Toast.LENGTH_SHORT).show();
                    break;
                }else {
                    int aphone1 = Integer.parseInt(String.valueOf(phone.getText()));
                    a1.setPhone(aphone1);
                }

                save(a1);
                Toast.makeText(mContext,"添加联系人成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnDelete:
                if(id.getText().equals("")||id.getText().equals(" ")||id.getText() == null){
                    Toast.makeText(mContext,"请输入要删除的联系人id",Toast.LENGTH_SHORT).show();
                    break;
                }else {
                    int aid3 = Integer.parseInt(String.valueOf(id.getText()));
                    delete(aid3);
                }
                Toast.makeText(mContext,"删除联系人成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnBack:
                Intent intent = new Intent(AddActivity.this,AddressBookActivity.class);
                startActivity(intent);
                break;



        }

    }

    public void delete(Integer id)
    {
        db = myDBOpenHelper.getWritableDatabase();
        db.execSQL("DELETE FROM address_book WHERE id = ?",
                new String[]{id.toString()});
    }

    public void save(Address address)
    {
        db = myDBOpenHelper.getWritableDatabase();
        db.execSQL("INSERT INTO address_book(phone,name) values(?,?)",
                new String[]{String.valueOf(address.getPhone()),address.getName()});
    }

    public void update(Address address)
    {
        db = myDBOpenHelper.getWritableDatabase();
        db.execSQL("UPDATE address_book SET name = ?,phone = ? WHERE id = ?",
                new String[]{address.getName(), String.valueOf(address.getPhone()), String.valueOf(address.getId())});
    }

    public Address find(Integer id)
    {
        db = myDBOpenHelper.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM address_book WHERE id = ?",
                new String[]{id.toString()});
        //存在数据才返回true
        if(cursor.moveToFirst())
        {
            int aid = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int phone = cursor.getInt(cursor.getColumnIndex("phone"));
            return new Address(name,0,phone,aid);
        }
        cursor.close();
        return null;
    }
}
