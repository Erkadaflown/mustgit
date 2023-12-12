package com.example.anujin_biydaalt1;

public class User {
    private String name;
    private String phone;
    private String dateOfBirth;

    public User() {
    }

    public User(String name, String phone, String dateOfBirth) {
        this.name = name;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
