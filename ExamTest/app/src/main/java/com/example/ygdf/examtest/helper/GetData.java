package com.example.ygdf.examtest.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

public class GetData {

    public static byte[] getImage(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setConnectTimeout(5000);
        connection.setRequestMethod("GET");

        if(connection.getResponseCode() != 200){
            throw new RuntimeException("请求失败");
        }
        InputStream inputStream = connection.getInputStream();
        byte[] bytes = StreamTool.read(inputStream);
        inputStream.close();
        return bytes;
    }

    public static String getHtml(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setConnectTimeout(5000);
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == 200){
            InputStream inputStream = connection.getInputStream();
            byte[] bytes = StreamTool.read(inputStream);
            String html = new String(bytes,"utf-8");
            inputStream.close();
            return html;
        }
        return null;
    }
}
