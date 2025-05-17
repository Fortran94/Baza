package com.Fortran94.BazaApp.service;

import com.Fortran94.BazaApp.dao.EventDAO;
import com.Fortran94.BazaApp.dao.ParticipantDAO;
import com.Fortran94.BazaApp.model.Event;
import com.Fortran94.BazaApp.model.ParticipantUser;

import java.util.List;

public class EventService {

    private final ParticipantDAO participantDAO;
    private final EventDAO eventDAO;

    public EventService(ParticipantDAO participantDAO, EventDAO eventDAO) {
        this.participantDAO = participantDAO;
        this.eventDAO = eventDAO;
    }

    public void addEvent(Event event) {
        eventDAO.addEvent(event); // добавляем в список
    }

    public void deleteEvent (Event event) {
        eventDAO.deleteEvent(event.getId());
    }

    public List<Event> getAllEvents() {
        return eventDAO.getAllEvents();
    }

    public Event getEventById(int id) {
        return eventDAO.getEventById(id);
    }

    public List<ParticipantUser> getParticipantsByEvent(int eventId) {
        return eventDAO.getParticipantsByEvent(eventId);
    }

    public void updateEventInDao(int id, String newName, String newLocation, String newOrganizer, String newOverview,
                                 int newQuantityOfParticipant, String newType) {
        eventDAO.updateEvent(id, newName, newLocation, newOrganizer, newOverview, newQuantityOfParticipant, newType);
    }

    }
