package com.Fortran94.BazaApp.context;

import com.Fortran94.BazaApp.dao.EventDAO;
import com.Fortran94.BazaApp.dao.ParticipantDAO;
import com.Fortran94.BazaApp.menu.MainMenu;
import com.Fortran94.BazaApp.service.EventService;
import com.Fortran94.BazaApp.service.ParticipantService;
import com.Fortran94.BazaApp.utils.EventMacker;
import com.Fortran94.BazaApp.utils.UserMacker;

import java.util.Scanner;

public class ApplicationContext {

    private final Scanner scanner;
    private final ParticipantDAO participantDAO;
    private final EventDAO eventDAO;
    private final EventService eventService;
    private final MainMenu mainMenu;
    private final ParticipantService participantService;
    private final UserMacker userMacker;
    private final EventMacker eventMacker;


    public ApplicationContext() {
        this.scanner = new Scanner(System.in);
        this.participantDAO = new ParticipantDAO();
        this.eventDAO = new EventDAO();
        this.eventService = new EventService(participantDAO, eventDAO);
        this.participantService = new ParticipantService(participantDAO, eventDAO);
        this.userMacker = new UserMacker(participantService, scanner);
        this.eventMacker = new EventMacker(eventService, scanner);
        this.mainMenu = new MainMenu(participantService, scanner, eventService, userMacker, eventMacker);

    }

    public UserMacker getUserMacker() {
        return userMacker;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public ParticipantDAO getParticipantDAO() {
        return participantDAO;
    }

    public EventDAO getEventDAO() {
        return eventDAO;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public ParticipantService getParticipantService() {
        return participantService;
    }
}
