package com.example.ygdf.prj2;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import static android.graphics.Color.red;

public class ColorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        Button btnChange = findViewById(R.id.btnChange);
        final TextView tvRed = findViewById(R.id.tvRed);
        final TextView tvGreen = findViewById(R.id.tvGreen);
        final TextView tvBlue = findViewById(R.id.tvBlue);
        final TextView tvPanel = findViewById(R.id.tvPanel);
        final TextView tvR = findViewById(R.id.tvR);
        final TextView tvG = findViewById(R.id.tvG);
        final TextView tvB = findViewById(R.id.tvB);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random iRandom = new Random();
                Integer iRed = iRandom.nextInt(256);
                Integer iGreen = iRandom.nextInt(256);
                Integer iBlue = iRandom.nextInt(256);

                tvRed.setText(iRed.toString());
                tvGreen.setText(iGreen.toString());
                tvBlue.setText(iBlue.toString());
//                tvR.setText("R");
//                tvG.setText("G");
//                tvB.setText("B");

                tvRed.setTextColor(Color.rgb(iRed,0,0));
                tvGreen.setTextColor(Color.rgb(0,iGreen,0));
                tvBlue.setTextColor(Color.rgb(0,0,iBlue));
                tvR.setTextColor(Color.rgb(iRed,0,0));
                tvG.setTextColor(Color.rgb(0,iGreen,0));
                tvB.setTextColor(Color.rgb(0,0,iBlue));

                tvPanel.setBackgroundColor(Color.rgb(iRed,iGreen,iBlue));
            }
        });
    }

}
