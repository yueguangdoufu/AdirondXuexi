package com.example.ygdf.xmldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ygdf.xmldemo.Helper.SaxHelper;
import com.example.ygdf.xmldemo.pojo.Person;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSax;
    private Button btnDom;
    private Button btnPull;
    private Button btnPull2;
    private TextView txtName;
    private TextView txtAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binds();
    }

    private void binds(){
        btnSax = findViewById(R.id.btnSax);
        btnDom = findViewById(R.id.btnDom);
        btnPull = findViewById(R.id.btnPull);
        btnPull2 = findViewById(R.id.btnPull2);
        txtName = findViewById(R.id.txtName);
        txtAge = findViewById(R.id.txtAge);

        btnSax.setOnClickListener(this);
        btnDom.setOnClickListener(this);
        btnPull.setOnClickListener(this);
        btnPull2.setOnClickListener(this);
    }

    private ArrayList<Person> readxmlForSAX() throws Exception{
        InputStream is = getAssets().open("person1.xml");
        SaxHelper ss = new SaxHelper();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        parser.parse(is,ss);
        is.close();
        return ss.getPersons();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSax:
                try {
                    readxmlForSAX();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnDom:
                break;
            case R.id.btnPull:
                break;
            case R.id.btnPull2:
                break;
        }
    }
}
