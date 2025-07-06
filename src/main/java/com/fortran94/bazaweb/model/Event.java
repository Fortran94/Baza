package com.fortran94.bazaweb.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name, location, organizer, overview, type, result;
    @Column(name = "quantity_of_participants")
    private int quantityOfParticipant;
    @ManyToMany
    @JoinTable(
            name = "event_participants",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private List<ParticipantUser> participants = new ArrayList<>();
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    public List<ParticipantUser> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantUser> participants) {
        this.participants = participants;
    }

    public Event(long id, String name, String location, String organizer, String overview, int quantityOfParticipant,
                 String type, LocalDate date) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.organizer = organizer;
        this.overview = overview;
        this.quantityOfParticipant = quantityOfParticipant;
        this.type = type;
        this.date = date;
    }

    public Event() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getQuantityOfParticipant() {
        return quantityOfParticipant;
    }

    public void setQuantityOfParticipant(int quantityOfParticipant) {
        this.quantityOfParticipant = quantityOfParticipant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTranslatedType(String type) {
        if (type.equalsIgnoreCase("Game")) {
            type = "Игра";
        } else if (type.equalsIgnoreCase("Tournament")) {
            type = "Турнир";
        }
        return type;
    }


    @Override
    public String toString() {
        return
                "\nНазвание: " + name + '\n' +
                        "Локация: " + location + '\n' +
                        "Организатор: " + organizer + '\n' +
                        "Описание: " + overview + '\n' +
                        // "Наших участников: " + quantityOfParticipantOur + '\n' +
                        "Количество участников всего: " + quantityOfParticipant + '\n' +
                        "Тип мероприятия: " + type;
    }
}
