package com.example.ygdf.examtest.pojo;

public class Person {
    private String name;
    private String wrod;
    private int img;

    public Person(String name, String wrod, int img) {
        this.name = name;
        this.wrod = wrod;
        this.img = img;
    }

    public Person() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWrod() {
        return wrod;
    }

    public void setWrod(String wrod) {
        this.wrod = wrod;
    }
}
