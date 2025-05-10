package com.Fortran94.BazaApp.context;

import com.Fortran94.BazaApp.dao.EventDAO;
import com.Fortran94.BazaApp.dao.ParticipantDAO;
import com.Fortran94.BazaApp.menu.MainMenu;

import java.util.Scanner;

public class ApplicationContext {

    private final Scanner scanner;
    private final ParticipantDAO participantDAO;
    private final EventDAO eventDAO;
    private final MainMenu mainMenu;


    public ApplicationContext() {
        this.scanner = new Scanner(System.in);
        this.participantDAO = new ParticipantDAO();
        this.eventDAO = new EventDAO();
        this.mainMenu = new MainMenu(participantDAO, eventDAO, scanner);
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

}
