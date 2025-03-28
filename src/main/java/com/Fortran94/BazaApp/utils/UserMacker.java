package com.Fortran94.BazaApp.utils;

import com.Fortran94.BazaApp.model.ParticipantUser;

import java.util.Scanner;

public class UserMacker {
    public static ParticipantUser writer () {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = input.nextLine();
        System.out.println();
        System.out.print("Введите фамилию: ");
        String surname = input.nextLine();
        System.out.println();
        System.out.print("Введите позывной: ");
        String callSign = input.nextLine();
        System.out.println();
        System.out.print("Введите возраст: ");
        int age = input.nextInt();
        input.nextLine(); // Очистка буфера после nextInt()

        return new ParticipantUser(0, name, surname, callSign, age);
    }

}
