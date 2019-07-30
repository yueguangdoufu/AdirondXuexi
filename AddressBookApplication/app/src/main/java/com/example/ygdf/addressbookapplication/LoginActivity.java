package com.example.ygdf.addressbookapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageHead;
    private EditText name;
    private EditText password;
    private Button btnLogin;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bundle();
    }

    public void bundle(){
        mContext = LoginActivity.this;
        imageHead = findViewById(R.id.imageHead);
        name = findViewById(R.id.editName);
        password = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String aname = String.valueOf(name.getText());
        String apassword = String.valueOf(password.getText());
        if(aname.equals("")||aname.equals(" ")||aname == null){
            Toast.makeText(mContext,"请输入用户名",Toast.LENGTH_SHORT).show();
            return;
        }
        if(aname.equals("ygdf") && apassword.equals("123456")){
            Intent intent = new Intent(LoginActivity.this,AddressBookActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(mContext,"用户名或密码错误",Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
