package com.Fortran94.BazaApp.model;

import java.util.ArrayList;
import java.util.List;

public class ParticipantUser extends User {
    private int numberOfEvents;
    private List<Integer> eventIds = new ArrayList<>();
    private java.sql.Date registrationDate;
    private int experiencePerMonth;


    public ParticipantUser(int id, String name, String surname, String callSign, int age, java.sql.Date registrationDate) {
        super(id, name, surname, callSign, age);
        this.registrationDate = registrationDate;
    }


    public java.sql.Date getRegistrationDate() {
        return new java.sql.Date(registrationDate.getTime());
    }


    public int getExperiencePerMonth() {
        return experiencePerMonth;
    }

    public void setExperiencePerMonth(int experiencePerMonth) {
        this.experiencePerMonth = experiencePerMonth;
    }

    public int getNumberOfEvents() {
        return numberOfEvents;
    }

    public void setNumberOfEvents(int numberOfEvents) {
        this.numberOfEvents = numberOfEvents;
    }

    private double activityLevel() {
        return Math.round(((double) this.numberOfEvents / this.experiencePerMonth) * 100.0) / 100.0;
    }

    public void takePart() {
        numberOfEvents++;
    }

    //TODO сделать абстрактный метод
    @Override
    public String toString() {
        return
                "\n Имя: " + getName() +
                        "\n Фамилия: " + getSurname() +
                        "\n Позывной: " + getCallSign() +
                        "\n Возраст: " + getAge() +
                        "\n Стаж участника в месяцах: " + getRegistrationDate() +
                        "\n Количество посещенных мероприятия: " + getNumberOfEvents() +
                        "\n Уровень активности: " + activityLevel();
    }
}
