package com.Fortran94.BazaApp;

import com.Fortran94.BazaApp.menu.MainMenu;

public class Main {
    public static void main(String[] args) {


        //Создаем список
        ParticipantList participantList = new ParticipantList();
        //Создаем меню
        MainMenu mainMenu = new MainMenu();
        //Отрисовываем меню
        mainMenu.printFirstMenu(participantList);

    }
}
