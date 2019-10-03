package com.eatoday.model;

public class User {
    private static String name;
    private static String lastName;
    private static String email;
    private static String password;
    private static String address;
    private static Boolean isLog = false;
/*
    public User(String name, String lastName, String email, String password, String address) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        isLog = true;
    }*/

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        User.lastName = lastName;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        User.address = address;
    }

    public static Boolean getIsLog() {
        return isLog;
    }

    public static void setIsLog(Boolean isLog) {
        User.isLog = isLog;
    }
}
