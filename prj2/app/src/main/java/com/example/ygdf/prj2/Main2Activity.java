package com.example.ygdf.prj2;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main2Activity extends Activity {

    private LinearLayout rootView;
    private LinearLayout myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dy_linear);
        rootView = findViewById(R.id.rootView);

        Button btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = new TextView(Main2Activity.this);
                textView.setText("代码在此2");
                textView.setBackgroundColor(Color.parseColor("#ff0000"));
                textView.setGravity(Gravity.CENTER);
                rootView.addView(textView);

            }
        });


    }

}
