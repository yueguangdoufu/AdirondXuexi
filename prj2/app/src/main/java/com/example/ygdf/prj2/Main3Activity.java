package com.example.ygdf.prj2;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main3Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button btnOk = findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("Verbose","this is Verbose");
                Log.d("Debug","this is Debug");
                Log.i("Info","this is Info");
                Log.w("Warming","this is Warming");
                Log.e("Error","this is Error");

                Toast.makeText(Main3Activity.this,"我是一个吐息",Toast.LENGTH_LONG).show();
            }
        });
    }

}
