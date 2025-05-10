package com.Fortran94.BazaApp.model;

public class Tournament extends Event {


    public Tournament(int id, String name, String location, String organizer, String overview, int quantityOfParticipantAll, String type) {
        super(id,
                name,
                location,
                organizer,
                overview,
                quantityOfParticipantAll,
                "tournament");
        ///todo хеш меп с участником - результатом
    }
}