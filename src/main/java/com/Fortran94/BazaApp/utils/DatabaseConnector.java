package com.Fortran94.BazaApp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

        private static final String URL = "jdbc:postgresql://localhost:5432/airsoft_db";
        private static final String USER = "airsoft_user"; // твой пользователь
        private static final String PASSWORD = "123"; // твой пароль

        public static Connection connect() {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.out.println("Ошибка подключения к базе данных");
                e.printStackTrace();
                return null;
            }
        }
    }


