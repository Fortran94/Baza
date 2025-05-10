package com.Fortran94.BazaApp.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Event {
    int id;
    private String name, location, organizer, overview;
    private int quantityOfParticipantAll;
    private String type;
    private List<Integer> participanIds = new ArrayList<>();




    public Event(int id, String name, String location, String organizer, String overview, int quantityOfParticipantAll, String type) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.organizer = organizer;
        this.overview = overview;
        this.quantityOfParticipantAll = quantityOfParticipantAll;
        this.type = type;

    }

    public int getId() {
        return id;
    }

    public void setQuantityOfParticipantAll(int quantityOfParticipantAll) {
        this.quantityOfParticipantAll = quantityOfParticipantAll;
    }

    public void setId(int id) {
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

    public int getQuantityOfParticipantAll() {
        return quantityOfParticipantAll;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        if (type.equalsIgnoreCase("Game")) {
            type = "Игра";
        } else if (type.equalsIgnoreCase("Tournament")) {
            type = "Турнир";
        }
        return
                "\nНазвание: " + name + '\n' +
                        "Локация: " + location + '\n' +
                        "Организатор: " + organizer + '\n' +
                        "Описание: " + overview + '\n' +
                        // "Наших участников: " + quantityOfParticipantOur + '\n' +
                        "Количество участников всего: " + quantityOfParticipantAll + '\n' +
                        "Тип мероприятия: " + type;
    }


}