package com.fortran94.bazaweb.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "participants")
public class ParticipantUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private int age;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "birth_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;
    @Column(name = "call_sign")
    private String callSign;
    @Column(name = "telephone_number")
    private String telephoneNumber;
    @Column(name = "avatar_path")
    private String avatarPath;
    @Column(name = "number_of_events")
    private int numberOfEvents;
    @ManyToMany(mappedBy = "participants")
    private List<Event> events = new ArrayList<>();
    @ManyToMany(mappedBy = "participants")
    private List<Training> trainings = new ArrayList<>();
    @Column(name = "registration_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate registrationDate;
    @Transient
    private int experiencePerMonth;
    @Column(length = 1000)
    private String characteristics;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role = Role.USER; // По умолчанию User
    @Column(name = "password")
    private String password;


    public ParticipantUser(
            Long id, String name, String surname, String callSign, LocalDate dateOfBirth,
            LocalDate registrationDate, int numberOfEvents, int experiencePerMonth,
            String telephoneNumber, String characteristics, String avatarPath) {

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.callSign = callSign;
        this.dateOfBirth = dateOfBirth;
        this.telephoneNumber = telephoneNumber;
        this.avatarPath = avatarPath;
        this.registrationDate = registrationDate;
        this.numberOfEvents = numberOfEvents;
        this.experiencePerMonth = experiencePerMonth;
        this.characteristics = characteristics;
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

    public int getAge() {
        if (this.dateOfBirth == null) {
            return 0; //TODO обработать исключение
        }

        return Period.between(getDateOfBirth(), LocalDate.now()).getYears();
    }
}

