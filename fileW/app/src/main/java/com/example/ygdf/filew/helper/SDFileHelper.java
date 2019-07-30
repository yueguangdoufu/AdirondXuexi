package com.example.ygdf.filew.helper;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SDFileHelper {

    private Context context;

    public SDFileHelper() {
    }

    public SDFileHelper(Context context){
        super();
        this.context = context;
    }

    public void save(String filename,String filecontent) throws IOException {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + filename;
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            fileOutputStream.write(filecontent.getBytes());
            fileOutputStream.close();
        }else {
            Toast.makeText(context,"sd卡不存在或者不可写入",Toast.LENGTH_SHORT).show();
        }
    }

    public String read(String filename) throws IOException {

        StringBuilder sb = new StringBuilder("");
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() +"/"+ filename;
            FileInputStream fileInputStream = new FileInputStream(filename);
            byte[] temp =new byte[1024];
            int len = 0;
            while( (len = fileInputStream.read(temp)) > 0){
                sb.append(new String(temp,0,len));
            }
            fileInputStream.close();

        }
        return sb.toString();
    }
}
