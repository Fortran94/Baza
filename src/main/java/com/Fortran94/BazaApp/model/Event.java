package com.Fortran94.BazaApp.model;

import java.util.ArrayList;
import java.util.List;
//todo Сделать абстрактным, сделан не абстрактным для теста
public class Event {
    int id;
    private String name, location, organizer, overview;
    private int quantityOfParticipant;
    private List<Integer> participanIds = new ArrayList<>();



    public Event(int id, String name, String location, String organizer, String overview, int quantityOfParticipant) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.organizer = organizer;
        this.overview = overview;
        this.quantityOfParticipant = quantityOfParticipant;
    }

    public int getId() {
        return id;
    }

    public void setQuantityOfParticipant(int quantityOfParticipant) {
        this.quantityOfParticipant = quantityOfParticipant;
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

    public int getQuantityOfParticipant() {
        return quantityOfParticipant;
    }

    @Override
    public String toString() {
        return
                "\nНазвание: " + name + '\n' +
                "Локация: " + location + '\n' +
                "Организатор: " + organizer + '\n' +
                "Описание: " + overview + '\n' +
                "Количество участников: " + quantityOfParticipant;
    }


}
