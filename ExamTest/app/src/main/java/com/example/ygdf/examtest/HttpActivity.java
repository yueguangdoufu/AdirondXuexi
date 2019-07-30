package com.example.ygdf.examtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ygdf.examtest.helper.GetData;

import java.io.IOException;

public class HttpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnImg;
    private Button btnDai;
    private Button btnWang;
    private ImageView imgWang;
    private ScrollView scroll;
    private TextView txtWang;
    private Bitmap bitmap;
    private WebView web;

    private String detail = "";
    private final static String pic = "https://ww2.sinaimg.cn/large/7a8aed7bgw1evshgr5z3oj20hs0qo0vq.jpg";
    private final static String html = "https://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        bind();

    }

    private void bind(){
        btnImg = findViewById(R.id.btnImg);
        btnDai = findViewById(R.id.btnDai);
        btnWang = findViewById(R.id.btnWang);
        scroll = findViewById(R.id.scroll);
        txtWang = findViewById(R.id.txtWang);
        web = findViewById(R.id.web);
        imgWang = findViewById(R.id.imgWang);

        btnDai.setOnClickListener(this);
        btnImg.setOnClickListener(this);
        btnWang.setOnClickListener(this);
    }

    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg){
            switch (msg.what){
                case 0x001:
                    hideAllWidget();
                    imgWang.setVisibility(View.VISIBLE);
                    imgWang.setImageBitmap(bitmap);
                    Toast.makeText(HttpActivity.this,"图片加载完毕",Toast.LENGTH_SHORT).show();
                    break;
                case 0x002:
                    hideAllWidget();
                    scroll.setVisibility(View.VISIBLE);
                    txtWang.setText(detail);
                    Toast.makeText(HttpActivity.this,"Html代码加载完毕",Toast.LENGTH_SHORT).show();
                    break;
                case 0x003:
                    hideAllWidget();
                    web.setVisibility(View.VISIBLE);
                    web.loadDataWithBaseURL("",detail,"text/html","utf-8","");
                    Toast.makeText(HttpActivity.this,"加载html完毕",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void hideAllWidget() {
        scroll.setVisibility(View.GONE);
        imgWang.setVisibility(View.GONE);
        web.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnImg:
                new Thread(){
                    public void run(){
                        try {
                            byte[] data = GetData.getImage(pic);
                            bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0x001);
                    }
                }.start();
                break;
            case R.id.btnDai:
                new Thread(){
                    public  void run(){
                        try {
                            detail = GetData.getHtml(html);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0x002);
                    }
                }.start();
                break;
            case R.id.btnWang:
                if(detail.equals("")){
                    Toast.makeText(HttpActivity.this,"请先请求Html代码",Toast.LENGTH_SHORT).show();
                }else {
                    handler.sendEmptyMessage(0x003);
                }
                break;
        }

    }


}
