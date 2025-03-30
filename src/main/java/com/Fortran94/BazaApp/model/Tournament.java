package com.Fortran94.BazaApp.model;

public class Tournament extends Event {

    public Tournament(int id, String name, String location, String organizer, String overview, int quantityOfParticipant) {
        super(id, name, location, organizer, overview, quantityOfParticipant);
        ///todo хеш меп с участником - результатом
    }
}
