package com.example.ygdf.filew.pojo;

public class Data {
    private int imgId;
    private String content;

    public Data(int imgId, String content) {
        this.imgId = imgId;
        this.content = content;
    }

    public Data() {
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
