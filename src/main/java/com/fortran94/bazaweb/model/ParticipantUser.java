package com.fortran94.bazaweb.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "participants")
public class ParticipantUser extends User {
    @Column(name = "number_of_events")
    private int numberOfEvents;
    @ManyToMany(mappedBy = "participants")
    private List<Event> events = new ArrayList<>();

    @Column(name = "registration_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate registrationDate;
    @Transient
    private int experiencePerMonth;


    public ParticipantUser(long id, String name, String surname, String callSign, LocalDate dateOfBirth,
                           LocalDate registrationDate, int numberOfEvents, int experiencePerMonth, long telephoneNumber) {
        super(id, name, surname, callSign, dateOfBirth, telephoneNumber);
        this.registrationDate = registrationDate;
        this.numberOfEvents = numberOfEvents;
        this.experiencePerMonth = experiencePerMonth;
    }

    public ParticipantUser() {
        super();
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getRegistrationDate() {
            return registrationDate;
    }


    public int getExperiencePerMonth() {
        return getMonthsSinceRegistration();
    }

//    public void setExperiencePerMonth(int experiencePerMonth) {
//        this.experiencePerMonth = experiencePerMonth;
//    }

    public int getNumberOfEvents() {
        return this.events.size();
    }

    public void setNumberOfEvents(int numberOfEvents) {

        this.numberOfEvents = numberOfEvents;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


    public double getActivityLevel() {
        int months = getMonthsSinceRegistration();
        if (months == 0) return 0.0;

        return Math.round(((double) getNumberOfEvents() / months) * 100.0) / 100.0;
    }


    //todo сейчас стаж считается в java "На лету" и не хранится в БД, возможно в будущем стоит переделать
    public int getMonthsSinceRegistration() {
        if (registrationDate == null) {
            return 1;
        }

        LocalDate regDate = registrationDate;
        LocalDate now = LocalDate.now();
        Period period = Period.between(regDate, now);
        int months = period.getYears() * 12 + period.getMonths();
        return Math.max(months, 1);
    }


    //TODO сделать абстрактный метод???
    @Override
    public String toString() {
        return
                "\n Имя: " + getName() +
                        "\n Фамилия: " + getSurname() +
                        "\n Позывной: " + getCallSign() +
                        "\n Возраст: " + getAge() +
                        "\n Стаж участника в месяцах: " + getMonthsSinceRegistration() +
                        "\n Количество посещенных мероприятий: " + getNumberOfEvents() +
                        "\n Уровень активности: " + getActivityLevel();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipantUser)) return false;
        ParticipantUser that = (ParticipantUser) o;
        return this.getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Long.hashCode(getId());
    }

}

