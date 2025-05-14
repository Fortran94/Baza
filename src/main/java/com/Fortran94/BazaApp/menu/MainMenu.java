package com.Fortran94.BazaApp.menu;

import com.Fortran94.BazaApp.dao.EventDAO;
import com.Fortran94.BazaApp.dao.ParticipantDAO;
import com.Fortran94.BazaApp.model.Logo;
import com.Fortran94.BazaApp.service.ParticipantService;

import java.util.Scanner;

public class MainMenu {

    private final Scanner scanner;
    private final ParticipantMenu participantMenu;
    private final EventMenu eventMenu;
    private final ParticipantService participantService;

    public MainMenu(ParticipantService participantService, EventDAO eventDAO, Scanner scanner) {
        this.scanner = scanner;
        this.participantService = participantService;
        this.participantMenu = new ParticipantMenu(participantService, eventDAO, scanner);
        this.eventMenu = new EventMenu(participantService, eventDAO, scanner);
    }

    public void printMainMenu() {

        Logo logo = new Logo();
        logo.printLogo();

        while (true) {
            showMenuText();
            if (scanner.hasNextInt()) {
                int menuItem = scanner.nextInt();
                if (menuItem == 1) {
                    participantMenu.printParticipantMenu();
                } else if (menuItem == 2) {
                    eventMenu.printEventMenu();
                } else if (menuItem == 0) { // Выход из приложения
                    System.out.println("До свидания!");
                    System.exit(0);
                }else {
                    System.out.println("Некорректный пункт меню!" +
                            "\nВведите корректный номер пункта меню!");
                }
            }
        }
    }

    public void showMenuText () {
        System.out.println("Вы находитесь в главном меню" +
                "\n Выберите пункт меню");
        System.out.println();
        System.out.println("1. Участники" +
                "\n2. Мероприятия" +
                "\n " +
                "\nДля выхода нажмите 0");
        System.out.println();
    }
}
