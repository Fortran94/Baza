package com.Fortran94.BazaApp.model;

public class Game extends Event {

    public Game(int id, String name, String location, String organizer, String overview, int quantityOfParticipant, String type) {
        super(id, name, location, organizer, overview, quantityOfParticipant, "game");
    }


}