package com.Fortran94.BazaApp.utils;

import com.Fortran94.BazaApp.dao.ParticipantDAO;
import com.Fortran94.BazaApp.model.ParticipantUser;

import java.util.Scanner;

public class UserMacker {


    public static ParticipantUser writer () {
        Scanner input = new Scanner(System.in);

        String name = "";
        while (name.isEmpty()) {
            System.out.print("Введите имя: ");
            name = input.nextLine().trim();
            if (name.isEmpty()) System.out.println("Имя не может быть пустым.");
        }

        String surname = "";
        while (surname.isEmpty()) {
            System.out.print("Введите фамилию: ");
            surname = input.nextLine().trim();
            if (surname.isEmpty()) System.out.println("Фамилия не может быть пустой.");
        }

        String callSign = "";
        while (callSign.isEmpty()) {
            System.out.print("Введите позывной: ");
            callSign = input.nextLine().trim();
            if (callSign.isEmpty()) System.out.println("Позывной не может быть пустым.");
        }

        int age = 0;
        while (age <= 0) {
            System.out.print("Введите возраст: ");
            if (input.hasNextInt()) {
                age = input.nextInt();
                if (age <= 0) System.out.println("Возраст должен быть больше 0.");
            } else {
                System.out.println("Введите корректное число.");
                input.next(); // пропускаем неправильный ввод
            }
            input.nextLine(); // очищаем буфер
        }

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