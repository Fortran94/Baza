package com.fortran94.bazaweb.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ParticipantUser extends User {
    private int numberOfEvents;
    private List<Integer> eventIds = new ArrayList<>();
    private java.sql.Date registrationDate;
    private int experiencePerMonth;


    public ParticipantUser(int id, String name, String surname, String callSign, int age, Date registrationDate) {
        super(id, name, surname, callSign, age);
        this.registrationDate = registrationDate;
        this.numberOfEvents = numberOfEvents;
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
        return Math.round(((double) getNumberOfEvents()  / getMonthsSinceRegistration()  * 100.0) / 100.0);
    }

    //todo сейчас стаж считается в java "На лету" и не хранится в БД, возможно в будущем стоит переделать
    public int getMonthsSinceRegistration() {
        LocalDate regDate = registrationDate.toLocalDate();
        LocalDate now = LocalDate.now();
        Period period = Period.between(regDate, now);
        int months = period.getYears() * 12 + period.getMonths();
        return Math.max(months, 1); // не меньше 1
    }

    //TODO сделать абстрактный метод
    @Override
    public String toString() {
        return
                "\n Имя: " + getName() +
                        "\n Фамилия: " + getSurname() +
                        "\n Позывной: " + getCallSign() +
                        "\n Возраст: " + getAge() +
                        "\n Стаж участника в месяцах: " + getMonthsSinceRegistration() +
                        "\n Количество посещенных мероприятий: " + getNumberOfEvents() +
                        "\n Уровень активности: " + activityLevel();
    }
}

