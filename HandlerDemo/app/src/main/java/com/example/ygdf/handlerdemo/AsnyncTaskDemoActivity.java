package com.example.ygdf.handlerdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class AsnyncTaskDemoActivity extends AppCompatActivity {

    private ImageView image = null;
    private Button show;
    private ProgressBar progressBar = null;
    private int number = 0;
    List<String> imageUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asnync_task_demo);

        progressBar = findViewById(R.id.processBar);
        image = findViewById(R.id.image);
        show = findViewById(R.id.show);
        show.setOnClickListener(new showButtonListener());

        imageUrl = new ArrayList<String>();
        imageUrl.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542170544486&di=edb4ccf8d35beeffb5396dcb5d21ad2d&imgtype=0&src=http%3A%2F%2Fwww.ahgame.com%2Fuploads%2Fallimg%2F171116%2F1-1G116153S3.jpg");
        imageUrl.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542170738432&di=145b7c8d26004a9a6d516d9f1e8c6988&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F31f37290269333242ad144bef5f649780e114497.jpg");
        imageUrl.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542170770111&di=7c2284ddbf2954f4d0f8dd7dd3dcaed5&imgtype=0&src=http%3A%2F%2Fwww.deskier.com%2Fuploads%2Fallimg%2F170710%2F1-1FG0214541.jpg");
        imageUrl.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542170816474&di=9635c8ca68526e5a1957c8eba742aa59&imgtype=0&src=http%3A%2F%2Fuploads.5068.com%2Fallimg%2F1712%2F151-1G212101411.jpg");
        imageUrl.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2704200296,4109710192&fm=26&gp=0.jpg");
    }

    private class showButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            number++;
            MyAsyncTask myAsyncTask = new MyAsyncTask(getApplicationContext());
            myAsyncTask.execute(imageUrl.get(number % imageUrl.size()));

        }
    }

    class MyAsyncTask extends AsyncTask<String,Integer,Bitmap>{
        public MyAsyncTask(Context context) {
            progressBar.setVisibility(View.VISIBLE);
            image.setVisibility(View.GONE);
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            Bitmap bitmap = null;
            try {
                URL url = new URL(params[0]);

                URLConnection conn = url.openConnection();
                conn.connect();

                InputStream inputStream = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                Toast.makeText(getApplicationContext(),"传回图片了",Toast.LENGTH_SHORT).show();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap){
            progressBar.setVisibility(View.GONE);
            image.setVisibility(View.VISIBLE);
            if(bitmap != null){
                image.setImageBitmap(bitmap);
            }else {
                Toast.makeText(getApplicationContext(),"网络异常",Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        protected void onPreExecute(){
            Toast.makeText(getApplicationContext(),"任务开始.......",Toast.LENGTH_LONG).show();
        }

    }
}
