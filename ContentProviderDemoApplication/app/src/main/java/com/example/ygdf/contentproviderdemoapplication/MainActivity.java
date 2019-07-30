package com.example.ygdf.contentproviderdemoapplication;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private EditText txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
    }

    private void bindView(){
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        txt1 = findViewById(R.id.txt1);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    private void getMsgs(){
        Uri uri = Uri.parse("content://sms/");
        ContentResolver resolver = getContentResolver();
        //获取的是哪些列的信息
        Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
        while(cursor.moveToNext())
        {
            String address = cursor.getString(0);
            String date = cursor.getString(1);
            String type = cursor.getString(2);
            String body = cursor.getString(3);
            System.out.println("地址:" + address);
            System.out.println("时间:" + date);
            System.out.println("类型:" + type);
            System.out.println("内容:" + body);
            System.out.println("======================");
        }
        cursor.close();
    }

    private void insertMsg() {
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://sms/");
        ContentValues conValues = new ContentValues();
        conValues.put("address", "123456789");
        conValues.put("type", 1);
        conValues.put("date", System.currentTimeMillis());
        conValues.put("body", "no zuo no die why you try!");
        resolver.insert(uri, conValues);
        Log.e("HeHe", "短信插入完毕~");
    }

    private void getContacts(){
        //①查询raw_contacts表获得联系人的id
        ContentResolver resolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        //查询联系人数据
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while(cursor.moveToNext())
        {
            //获取联系人姓名,手机号码
            String cName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String cNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            System.out.println("姓名:" + cName);
            System.out.println("号码:" + cNum);
            System.out.println("======================");
        }
        cursor.close();
    }

    private void queryContact(String number){
        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/" + number);
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"display_name"}, null, null, null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(0);
            System.out.println(number + "对应的联系人名称：" + name);
        }
        cursor.close();
    }

    private void AddContact() throws RemoteException, OperationApplicationException {
        //使用事务添加联系人
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri dataUri =  Uri.parse("content://com.android.contacts/data");

        ContentResolver resolver = getContentResolver();
        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
        ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
                .withValue("account_name", null)
                .build();
        operations.add(op1);

        //依次是姓名，号码，邮编
        ContentProviderOperation op2 = ContentProviderOperation.newInsert(dataUri)
                .withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/name")
                .withValue("data2", "Coder-pig")
                .build();
        operations.add(op2);

        ContentProviderOperation op3 = ContentProviderOperation.newInsert(dataUri)
                .withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
                .withValue("data1", "13798988888")
                .withValue("data2", "2")
                .build();
        operations.add(op3);

        ContentProviderOperation op4 = ContentProviderOperation.newInsert(dataUri)
                .withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/email_v2")
                .withValue("data1", "779878443@qq.com")
                .withValue("data2", "2")
                .build();
        operations.add(op4);
        //将上述内容添加到手机联系人中~
        resolver.applyBatch("com.android.contacts", operations);
        Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                getMsgs();
                break;
            case R.id.btn2:
                insertMsg();
                break;
            case R.id.btn3:
                getContacts();
                break;
            case R.id.btn4:
                String number = txt1.getText().toString();
                queryContact(number);
                break;
            case R.id.btn5:
                try {
                    AddContact();
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (OperationApplicationException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
