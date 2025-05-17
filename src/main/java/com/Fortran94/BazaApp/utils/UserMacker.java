package com.Fortran94.BazaApp.utils;

import com.Fortran94.BazaApp.dao.ParticipantDAO;
import com.Fortran94.BazaApp.model.ParticipantUser;
import com.Fortran94.BazaApp.service.ParticipantService;

import java.sql.Date;
import java.util.Scanner;

public class UserMacker {

    private final ParticipantService participantService;
    private final Scanner scanner;

    public UserMacker(ParticipantService participantService, Scanner scanner) {
        this.participantService = participantService;
        this.scanner = scanner;
    }

    public ParticipantUser writer () {

        String name = "";
        while (name.isEmpty()) {
            System.out.print("Введите имя: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) System.out.println("Имя не может быть пустым.");
        }

        String surname = "";
        while (surname.isEmpty()) {
            System.out.print("Введите фамилию: ");
            surname = scanner.nextLine().trim();
            if (surname.isEmpty()) System.out.println("Фамилия не может быть пустой.");
        }

        String callSign = "";
        while (callSign.isEmpty()) {
            System.out.print("Введите позывной: ");
            callSign = scanner.nextLine().trim();
            if (callSign.isEmpty()) System.out.println("Позывной не может быть пустым.");
        }

        int age = 0;
        while (age <= 0) {
            System.out.print("Введите возраст: ");
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                if (age <= 0) System.out.println("Возраст должен быть больше 0.");
            } else {
                System.out.println("Введите корректное число.");
                scanner.next(); // пропускаем неправильный ввод
            }
            scanner.nextLine(); // очищаем буфер
        }

        Date registrationDate = new Date(System.currentTimeMillis());
        return new ParticipantUser(0, name, surname, callSign, age, registrationDate);
    }


    public void participantEdit (ParticipantUser participant, ParticipantService participantService) {
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
        participantService.updateParticipantInDao(participant.getId(), newName, newSurname, newCallSign, newAge);

    }

}