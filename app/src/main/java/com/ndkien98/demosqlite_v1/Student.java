package com.ndkien98.demosqlite_v1;


public class Student {

    int id;
    String name;
    String address;
    String phone_number;
    Integer image;

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


    public Student(int id, String name, String address, String phone_number) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }

    public Student(int id, String name, String address, String phone_number, Integer image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.image = image;
    }

    public Student() {
    }
}
