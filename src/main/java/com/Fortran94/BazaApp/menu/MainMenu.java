package com.Fortran94.BazaApp.menu;

import com.Fortran94.BazaApp.dao.EventDAO;
import com.Fortran94.BazaApp.dao.ParticipantDAO;
import com.Fortran94.BazaApp.model.Logo;

import java.util.Scanner;

public class MainMenu {
    public void printFirstMenu (ParticipantDAO participants, EventDAO events) {
        /*
         * Рисуем лого и выводим пункты меню
         */
        Logo logo = new Logo();
        logo.printLogo();
        Scanner scanner = new Scanner(System.in);
        ParticipantMenu participantMenu = new ParticipantMenu(participants);
        EventMenu eventMenu = new EventMenu(events);


        while (true) {
            showMenuText();
            if (scanner.hasNextInt()) {
                int menuItem = scanner.nextInt();
                if (menuItem == 1) {
                    participantMenu.printParticipantMenu(participants); //todo Переименовать тут партисипантс в партисипантдао
                } else if (menuItem == 2) {
                    eventMenu.printEventMenu(events); //todo Переименовать тут эвентс в эвентдао
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
