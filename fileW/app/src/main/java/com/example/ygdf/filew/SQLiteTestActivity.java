package com.example.ygdf.filew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ygdf.filew.helper.MySQLiteOpenHelper;

public class SQLiteTestActivity extends Activity implements View.OnClickListener{

    private Context mContext;
    private Button btn_insert;
    private Button btn_query;
    private Button btn_update;
    private Button btn_delete;
    private SQLiteDatabase db;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private StringBuilder sb;
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_test);
        mContext = SQLiteTestActivity.this;
        mySQLiteOpenHelper = new MySQLiteOpenHelper(mContext,"my.db",null,1);
        blindViews();
    }

    private void blindViews() {

        btn_insert = findViewById(R.id.btn1);
        btn_delete = findViewById(R.id.btn4);
        btn_query = findViewById(R.id.btn2);
        btn_update = findViewById(R.id.btn3);

        btn_update.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_insert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        db = mySQLiteOpenHelper.getWritableDatabase();
        switch (v.getId()){
            case R.id.btn1:
                ContentValues values1 = new ContentValues();
                values1.put("name","jj" + i);
                i++;
                db.insert("person",null,values1);
                Toast.makeText(mContext,"插入完毕",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                sb = new StringBuilder();
                Cursor cursor = db.query("person",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        int pid = cursor.getInt(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        sb.append("id: "+pid+": "+name+"\n");

                    }while (cursor.moveToNext());
                }
                cursor.close();
                Toast.makeText(mContext,sb.toString(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn3:
                ContentValues values2 = new ContentValues();
                values2.put("name","kk");
                db.update("person",values2,"name = ?",new String[]{"jj2"});
                break;
            case R.id.btn4:
                db.delete("person","id = ?",new String[]{"3"});
                break;

        }

    }
}
