package com.Fortran94.BazaApp.model;

import java.util.ArrayList;
import java.util.List;
//todo Сделать абстрактным, сделан не абстрактным для теста
public class Event {
    int id;
    private String name, location, organizer, overview;
    private int quantityOfParticipantAll;
    private List<Integer> participanIds = new ArrayList<>();




    public Event(int id, String name, String location, String organizer, String overview, int quantityOfParticipantAll) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.organizer = organizer;
        this.overview = overview;
        this.quantityOfParticipantAll = quantityOfParticipantAll;

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

    @Override
    public String toString() {
        return
                "\nНазвание: " + name + '\n' +
                "Локация: " + location + '\n' +
                "Организатор: " + organizer + '\n' +
                "Описание: " + overview + '\n' +
               // "Наших участников: " + quantityOfParticipantOur + '\n' +
                "Количество участников всего: " + quantityOfParticipantAll;
    }


}
