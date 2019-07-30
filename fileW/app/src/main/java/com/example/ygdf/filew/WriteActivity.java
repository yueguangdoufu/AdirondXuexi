package com.example.ygdf.filew;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ygdf.filew.helper.FileHelper;
import com.example.ygdf.filew.helper.SDFileHelper;

import java.io.IOException;

public class WriteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameTv;
    private EditText editText;
    private Button btnW;
    private Button btnR;
    private Button btnC;
    private Button btnWsd;
    private Button btnRsd;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        mContext = getApplicationContext();
        bindView();
    }

    private void bindView() {
        nameTv = findViewById(R.id.nameTv);
        editText = findViewById(R.id.contentTv);
        btnW = findViewById(R.id.btnW);
        btnR = findViewById(R.id.btnR);
        btnC = findViewById(R.id.btnC);
        btnWsd = findViewById(R.id.btnWsd);
        btnRsd = findViewById(R.id.btnRsd);

        btnW.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnR.setOnClickListener(this);
        btnRsd.setOnClickListener(this);
        btnWsd.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnC:
                nameTv.setText("");
                editText.setText("");
                break;
            case R.id.btnW:
                FileHelper fileHelper = new FileHelper(mContext);
                String filename = nameTv.getText().toString();
                String filecontent = editText.getText().toString();
                try {
                    fileHelper.save(filename,filecontent);
                    Toast.makeText(getApplicationContext(),"数据写入成功",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"数据写入失败",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnR:
                String content = "";
                FileHelper fileHelper1 = new FileHelper(getApplicationContext());
                String fname = nameTv.getText().toString();
                try {
                    content = fileHelper1.read(fname);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),content,Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnWsd:
                SDFileHelper sdHelper = new SDFileHelper(mContext);
                String sdname = nameTv.getText().toString();
                String sdcontent = editText.getText().toString();
                try {
                    sdHelper.save(sdname,sdcontent);
                    Toast.makeText(getApplicationContext(),"数据写入成功",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"数据写入失败",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnRsd:
                String sdcon = "";
                SDFileHelper sdHelper1 = new SDFileHelper(mContext);
                String sdnam = nameTv.getText().toString();
                try {
                    sdcon = sdHelper1.read(sdnam);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),sdcon,Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
