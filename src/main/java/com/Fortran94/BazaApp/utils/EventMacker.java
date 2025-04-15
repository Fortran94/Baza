package com.Fortran94.BazaApp.utils;

import com.Fortran94.BazaApp.dao.EventDAO;
import com.Fortran94.BazaApp.model.Event;

import java.util.Scanner;

public class EventMacker {

    public static Event writer () {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите название: ");
        String name = input.nextLine();
        System.out.println();
        System.out.print("Введите локацию: ");
        String location = input.nextLine();
        System.out.println();
        System.out.print("Укажите организатора: ");
        String organizer = input.nextLine();
        System.out.println();
        System.out.print("Введите краткое описание: ");
        String overview = input.nextLine();
        System.out.println();
        System.out.print("Введите количество всех участников: ");
        int quantityOfParticipantAll = input.nextInt();
//        System.out.print("Введите количество наших участников: ");
//        int quantityOfParticipantOur = input.nextInt();
        input.nextLine(); // Очистка буфера после nextInt()
        java.sql.Date registrationDate = new java.sql.Date(System.currentTimeMillis());

        return new Event(0, name, location, organizer, overview, quantityOfParticipantAll);
    }

    public static void eventEdit (Event event, EventDAO eventDAO) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Редактирование мероприятия (оставьте поле пустым, чтобы не менять значение)");

        System.out.println("Введите новое название (" + event.getName() + "):");
        String newName = scanner.nextLine();
        if (newName.isEmpty()) {
            newName = event.getName();
        }

        System.out.println("Введите новую локацию (" + event.getLocation() + "):");
        String newLocation = scanner.nextLine();
        if (newLocation.isEmpty()) {
            newLocation = event.getLocation();
        }

        System.out.println("Укажите нового организатора (" + event.getOrganizer() + "):");
        String newOrganizer = scanner.nextLine();
        if (newOrganizer.isEmpty()) {
            newOrganizer = event.getOrganizer();
        }

        System.out.println("Введите новое описание (" + event.getOverview() + "):");
        String newOverview = scanner.nextLine();
        if (newOverview.isEmpty()) {
            newOverview = event.getOverview();
        }

        System.out.println("Введите новое количество участников (" + event.getQuantityOfParticipantAll() + "):");
        String quantityOfParticipantInput = scanner.nextLine();
        int newQuantityOfParticipant;
        if (quantityOfParticipantInput.isEmpty()) {
            newQuantityOfParticipant = event.getQuantityOfParticipantAll();
        } else {
            newQuantityOfParticipant = Integer.parseInt(quantityOfParticipantInput);
        }

        // Отправляем обновление в БД
        eventDAO.updateEvent(event.getId(), newName, newLocation, newOrganizer, newOverview, newQuantityOfParticipant);

    }
}
