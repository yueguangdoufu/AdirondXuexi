package com.example.ygdf.addressbookapplication.pojo;

import android.graphics.Bitmap;

public class Address {
    private String name;
    private int head;
    private int phone;
    private int id;

    public Address() {
    }

    public Address(String name, int head, int phone, int id) {
        this.name = name;
        this.head = head;
        this.phone = phone;
        this.id = id;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
