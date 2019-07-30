package com.example.ygdf.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.com.lsnu.train.myapplication.R;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.drawable.dog_icon);
        builder.setTitle("退出");
        builder.setMessage("你确定要退出吗?");

        builder.setPositiveButton("确定",this);
        builder.setNegativeButton("取消",this);
        builder.setNeutralButton("中间BTN",this);

        LinearLayout rot_lin = new LinearLayout(MainActivity.this);
        rot_lin.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams LP_FW = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        rot_lin.setLayoutParams(LP_FW);

        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setImageResource(R.drawable.dog);
        imageView.setLayoutParams(LP_FW);
        rot_lin.addView(imageView);

        TextView textView = new TextView(MainActivity.this);
        textView.setText("可爱的狗狗");
        textView.setTextSize(20);
        textView.setLayoutParams(LP_FW);
        rot_lin.addView(textView);


        builder.setView(rot_lin);
        builder.create();
        builder.show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

        switch (i){
            case DialogInterface.BUTTON_POSITIVE:

                break;
            case DialogInterface.BUTTON_NEGATIVE:
                break;
            case DialogInterface.BUTTON_NEUTRAL:
                break;
        }
    }
}
