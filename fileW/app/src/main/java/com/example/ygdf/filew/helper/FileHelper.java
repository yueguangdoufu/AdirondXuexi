package com.example.ygdf.filew.helper;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHelper {

    private Context mContext;

    public FileHelper(){

    }

    public FileHelper(Context mContext){
        super();
        this.mContext = mContext;
    }

    public void save(String filename,String filecontent) throws IOException {

        FileOutputStream outputStream = mContext.openFileOutput(filename,Context.MODE_PRIVATE);
        outputStream.write(filecontent.getBytes());
        outputStream.close();

    }

    public String read(String filename) throws IOException {

        FileInputStream fileInputStream = mContext.openFileInput(filename);
        byte[] temp = new byte[1024];
        StringBuilder sb = new StringBuilder("");
        int len = 0;
        while ( (len = fileInputStream.read(temp)) > 0){
            sb.append(new String(temp,0,len));
        }

        fileInputStream.close();
        return sb.toString();
    }
}
