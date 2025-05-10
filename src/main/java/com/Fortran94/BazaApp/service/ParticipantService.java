package com.Fortran94.BazaApp.service;


import com.Fortran94.BazaApp.dao.EventDAO;
import com.Fortran94.BazaApp.dao.ParticipantDAO;

public class ParticipantService {

    private final ParticipantDAO participantDAO;
    private final EventDAO eventDAO;

    public ParticipantService(ParticipantDAO participantDAO, EventDAO eventDAO) {
        this.participantDAO = participantDAO;
        this.eventDAO = eventDAO;
    }






}
