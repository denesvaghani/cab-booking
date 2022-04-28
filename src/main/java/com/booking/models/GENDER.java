package com.booking.models;

public enum  GENDER{
    MALE("m"),
    FEMALE("f");

    private String gender;

    GENDER(String gender){
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
