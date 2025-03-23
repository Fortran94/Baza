package com.Fortran94.BazaApp;

import com.Fortran94.BazaApp.menu.MainMenu;
import com.Fortran94.BazaApp.utils.DatabaseConnector;

public class Main {
    /*public static void main(String[] args) {


        //Создаем список
        ParticipantList participantList = new ParticipantList();
        //Создаем меню
        MainMenu mainMenu = new MainMenu();
        //Отрисовываем меню
        mainMenu.printFirstMenu(participantList);

    }*/

    public static void main(String[] args) {
        if (DatabaseConnector.connect() != null) {
            System.out.println("Подключение прошло успешно!");
        } else {
            System.out.println("Ошибка подключения.");
        }
    }

}
