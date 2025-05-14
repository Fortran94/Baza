package com.Fortran94.BazaApp.service;


import com.Fortran94.BazaApp.dao.EventDAO;
import com.Fortran94.BazaApp.dao.ParticipantDAO;
import com.Fortran94.BazaApp.model.Event;
import com.Fortran94.BazaApp.model.ParticipantUser;


import java.util.List;

public class ParticipantService {

    private final ParticipantDAO participantDAO;
    private final EventDAO eventDAO;


    public ParticipantService(ParticipantDAO participantDAO, EventDAO eventDAO) {
        this.participantDAO = participantDAO;
        this.eventDAO = eventDAO;

    }

    public void addParticipant(ParticipantUser participant) {
        participantDAO.addParticipant(participant);
    }

    public List<ParticipantUser> getAllParticipants (){
        return participantDAO.getAllParticipants();
    }

    private void editParticipant(ParticipantUser participant) {
        //todo
    }

    public void updateParticipantInDao(int id, String newName, String newSurname, String newCallSign, int newAge) {
       participantDAO.updateParticipant(id, newName, newSurname, newCallSign, newAge);
    }

    public void deleteParticipant (ParticipantUser participant) {
        participantDAO.deleteParticipant(participant.getId());
    }

    public int getEventsForParticipant(ParticipantUser participantUser) {
        return participantDAO.countEventsForParticipant(participantUser.getId());
    }

    public void addParticipantToEvent(int participantId, int eventId ) {
         participantDAO.addParticipantToEvent(participantId, eventId);
    }

    public ParticipantUser getParticipantById(int id) {
        return participantDAO.getParticipantById(id);
    }

    public List<Event> getEventsByParticipant (int participantId) {
       return participantDAO.getEventsByParticipant(participantId);
    }


}
