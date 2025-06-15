package com.Fortran94.BazaApp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String URL = "jdbc:postgresql://localhost:5432/airsoft_db";
    private static final String USER = "airsoft_user";
    private static final String PASSWORD = "123";

    public static Connection connect() {
        // Регистрируем драйвер PostgreSQL
        try {
            // Регистрируем драйвер вручную
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка загрузки драйвера PostgreSQL");
            e.printStackTrace();
            return null;
        }
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных");
            e.printStackTrace();
            return null;
        }
    }
}
