package com.fortran94.bazaweb.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;

@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Transient
    private int age;
    private String name, surname;
    @Column(name = "birth_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;
    @Column(name = "call_sign")
    private String callSign;
//    private long telephoneNumber;

    public User(long id, String name, String surname, String callSign, LocalDate dateOfBirth, long telephoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.callSign = callSign;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
//        this.telephoneNumber = telephoneNumber;
    }

    public User(){

    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
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
        if (this.dateOfBirth == null) {
            return 0; //  или можно бросить исключение, если надо
        }

        return Period.between(getDateOfBirth(), LocalDate.now()).getYears();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

//    public long getTelephoneNumber() {
//        return telephoneNumber;
//    }

//    public void setTelephoneNumber(long telephoneNumber) {
//        this.telephoneNumber = telephoneNumber;
//    }
}

