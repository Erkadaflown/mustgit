package com.example.temuujin_biydaalt1;

public class User {
    private String name;
    private String phone;

    public User() {
        // Default constructor required for Firebase
    }

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
