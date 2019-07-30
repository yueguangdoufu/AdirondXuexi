package com.example.ygdf.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ygdf.login.util.SharedPreferencesUtils;
import com.example.ygdf.login.widget.LoadingDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{

    private EditText name;
    private EditText password;
    private Button btnLog;
    private CheckBox remember;
    private Button forget;
    private Button register;

    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_login);

        initView();
        setupEvents();

    }

    public void initView(){
        name = findViewById(R.id.namText);
        password = findViewById(R.id.pasText);
        btnLog = findViewById(R.id.btnLog);
        remember = findViewById(R.id.remBox);
        forget = findViewById(R.id.btnFor);
        register = findViewById(R.id.btnReg);
    }

    public void setupEvents(){
        btnLog.setOnClickListener(this);
        forget.setOnClickListener(this);
        register.setOnClickListener(this);
        remember.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLog:
                loadUserName();
                login();
                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    public void loadUserName() {
        if (!getAccount().equals("") || !getAccount().equals("请输入登录账号")) {
            SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
            helper.putValues(new SharedPreferencesUtils.ContentValue("name", getAccount()));
        }

    }

    public void login(){
        if(getAccount().isEmpty()){
            showToast("你输入的账号为空!");
            return;
        }

        if(getPassword().isEmpty()){
            showToast("你输入的密码为空!");
            return;
        }

        showLoading();

    }

    public String getAccount(){
        return name.getText().toString().trim();
    }

    public String getPassword(){
        return password.getText().toString().trim();
    }

    public void showToast(final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
