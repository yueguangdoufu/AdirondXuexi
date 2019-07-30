package com.example.ygdf.prj2;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_rela2);

        final TextView tvMain = findViewById(R.id.tvMain);
        Button btn_1 = findViewById(R.id.btn_1);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Drawable[] drawables = tvMain.getCompoundDrawables();

               drawables[0].setBounds(0,0,100,100);
               tvMain.setCompoundDrawables(drawables[0],drawables[0],drawables[0],drawables[0]);

            }
        });

           }
}
