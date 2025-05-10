package com.Fortran94.BazaApp.menu;

import com.Fortran94.BazaApp.dao.EventDAO;
import com.Fortran94.BazaApp.dao.ParticipantDAO;
import com.Fortran94.BazaApp.model.Logo;

import java.util.Scanner;

public class MainMenu {

    private final Scanner scanner;
    private final ParticipantMenu participantMenu;
    private final EventMenu eventMenu;

    public MainMenu(ParticipantDAO participantDAO, EventDAO eventDAO, Scanner scanner) {
        this.scanner = scanner;
        this.participantMenu = new ParticipantMenu(participantDAO, eventDAO, scanner);
        this.eventMenu = new EventMenu(participantDAO, eventDAO, scanner);
    }

    public void printMainMenu() {

        Logo logo = new Logo(); //todo Внедрить зависимость
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
