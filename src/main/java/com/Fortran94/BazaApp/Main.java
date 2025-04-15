package com.Fortran94.BazaApp;

import com.Fortran94.BazaApp.dao.EventDAO;
import com.Fortran94.BazaApp.dao.ParticipantDAO;
import com.Fortran94.BazaApp.menu.MainMenu;
import com.Fortran94.BazaApp.model.ParticipantUser;
import com.Fortran94.BazaApp.utils.DatabaseConnector;


public class Main {
    public static void main(String[] args) {


        ParticipantDAO pD = new ParticipantDAO();
        EventDAO eD = new EventDAO();
        //Создаем меню
        MainMenu mainMenu = new MainMenu();
        //Отрисовываем меню
        mainMenu.printFirstMenu(pD, eD);

    }

}
