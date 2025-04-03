package com.Fortran94.BazaApp.utils;

import com.Fortran94.BazaApp.dao.ParticipantDAO;
import com.Fortran94.BazaApp.model.ParticipantUser;

import java.sql.Date;
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
        java.sql.Date registrationDate = new java.sql.Date(System.currentTimeMillis());

        return new ParticipantUser(0, name, surname, callSign, age, registrationDate);
    }

    public static void participantEdit (ParticipantUser participant, ParticipantDAO participantDAO) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Редактирование участника (оставьте поле пустым, чтобы не менять значение)");

        System.out.println("Введите новое имя (" + participant.getName() + "):");
        String newName = scanner.nextLine();
        if (newName.isEmpty()) {
            newName = participant.getName();
        }

        System.out.println("Введите новую фамилию (" + participant.getSurname() + "):");
        String newSurname = scanner.nextLine();
        if (newSurname.isEmpty()) {
            newSurname = participant.getSurname();
        }

        System.out.println("Введите новый позывной (" + participant.getCallSign() + "):");
        String newCallSign = scanner.nextLine();
        if (newCallSign.isEmpty()) {
            newCallSign = participant.getCallSign();
        }

        System.out.println("Введите новый возраст (" + participant.getAge() + "):");
        String ageInput = scanner.nextLine();
        int newAge;
        if (ageInput.isEmpty()) {
            newAge = participant.getAge();
        } else {
            newAge = Integer.parseInt(ageInput);
        }

        // Отправляем обновление в БД
        participantDAO.updateParticipant(participant.getId(), newName, newSurname, newCallSign, newAge);

    }

}
