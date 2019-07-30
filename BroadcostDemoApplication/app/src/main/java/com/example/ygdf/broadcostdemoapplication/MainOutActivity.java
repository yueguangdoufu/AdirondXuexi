package com.example.ygdf.broadcostdemoapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainOutActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnOk;
    private EditText ipNum;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_out);

        // 创建SharedPreferences对象
        sp=getSharedPreferences("config", MODE_PRIVATE);
        ipNum=(EditText) findViewById(R.id.editIpNum);
        // 从sp对象中获取存储的IP号码,并将号码显示到et_ipnumber控件中
        ipNum.setText(sp.getString("ipnumber",""));

        btnOk=findViewById(R.id.btnOk);

        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 获取用户输入的IP号码
        String ipnumber =ipNum.getText().toString().trim();
        // 创建Editor对象,保存用户输入的IP号码
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("ipnumber", ipnumber);
        editor.commit();
        Toast.makeText(this, "设置成功", Toast.LENGTH_LONG).show();
    }
}
