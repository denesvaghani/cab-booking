package com.booking.models;

public class User {
    private String userName;
    private GENDER gender;
    private int age;

    public User(String userName, GENDER gender, int age) {
        this.userName = userName;
        this.gender = gender;
        this.age = age;
    }

    public GENDER getGender() {
        return gender;
    }

    public String getUserName() {
        return userName;
    }

    public int getAge() {
        return age;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGender(GENDER gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
