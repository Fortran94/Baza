package com.Fortran94.BazaApp;

import com.Fortran94.BazaApp.model.Game;

import java.util.ArrayList;

public class GameList {

    ArrayList<Game> games = new ArrayList<>();

    //Выводит список игр по названию
    public void printGameNameList() {
        System.out.println("Для просмотра подробной информации об  игре введите номер игры" +
                "\n Для выхода нажмите 0");
        for (int i = 0; i < games.size(); i++) {
            System.out.println(i + 1 + " " + games.get(i).getName());
        }
    }
}
