package com.skyplus.topquiz.model;

public class User {

    private String Firstname;

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    @Override
    public String toString() {
        return "User{" +
                "Firstname='" + Firstname + '\'' +
                '}';
    }



}
