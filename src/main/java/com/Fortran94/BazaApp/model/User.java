package com.Fortran94.BazaApp.model;

public abstract class User {
    private int id, age;
    private String name, surname, callSign;



    public User(String name, String surname, String callSign, int age) {

        this.name = name;
        this.surname = surname;
        this.callSign = callSign;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
