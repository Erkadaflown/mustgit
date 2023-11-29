package com.example.erkabiydaalt;

public class User {
    private String name;
    private String phone;
    private String gender;

    public User() {
    }

    public User(String name, String phone, String gender) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }
}
