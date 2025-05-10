package com.Fortran94.BazaApp.utils;

import com.Fortran94.BazaApp.dao.EventDAO;
import com.Fortran94.BazaApp.model.Event;
import com.Fortran94.BazaApp.model.Game;
import com.Fortran94.BazaApp.model.Tournament;

import java.util.Scanner;

public class EventMacker {

    public static Event writer () {
        Scanner input = new Scanner(System.in);

        String type = "";
        while (type.isEmpty()) {
            System.out.print("Введите тип мероприятия: ");
            type = input.nextLine().trim();
            if (type.isEmpty()) System.out.println("Тип не может быть пустым.");
        }

        String name = "";
        while (name.isEmpty()) {
            System.out.print("Введите название: ");
            name = input.nextLine().trim();
            if (name.isEmpty()) System.out.println("Название не может быть пустым.");
        }

        String location = "";
        while (location.isEmpty()) {
            System.out.print("Введите локацию: ");
            location = input.nextLine().trim();
            if (location.isEmpty()) System.out.println("Локация не указана.");
        }

        String organizer = "";
        while (organizer.isEmpty()) {
            System.out.print("Введите организатора: ");
            organizer = input.nextLine().trim();
            if (organizer.isEmpty()) System.out.println("Организатор не указан.");
        }


        String overview = "";
        while (overview.isEmpty()) {
            System.out.print("Введите описание: ");
            overview = input.nextLine().trim();
            if (overview.isEmpty()) System.out.println("Описание не указано.");
        }

        int quantityOfParticipantAll = 0;
        while (quantityOfParticipantAll <= 0) {
            System.out.print("Введите количество участников: ");
            if (input.hasNextInt()) {
                quantityOfParticipantAll = input.nextInt();
                if (quantityOfParticipantAll <= 0) System.out.println("Количество должно быть больше 0.");
            } else {
                System.out.println("Введите корректное число.");
                input.next(); // пропускаем неправильный ввод
            }
            input.nextLine(); // очищаем буфер
        }
        //System.out.print("Введите количество наших участников: ");
        //int quantityOfParticipantOur = input.nextInt();

        if (type.equalsIgnoreCase("Игра")) {
            return new Game(0, name, location, organizer, overview, quantityOfParticipantAll, "Игра");
        }else if (type.equalsIgnoreCase("Турнир")){
            return new Tournament(0, name, location, organizer, overview, quantityOfParticipantAll, "Турнир");
        }

        return null;// Зачем это???
    }

    public static void eventEdit (Event event, EventDAO eventDAO) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Редактирование мероприятия (оставьте поле пустым, чтобы не менять значение)");

        System.out.println("Введите новый тип (" + event.getType() + "):");
        String newType = scanner.nextLine();
        if (newType.isEmpty()) {
            newType = event.getType();
        }

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
        eventDAO.updateEvent(event.getId(), newName, newLocation, newOrganizer, newOverview, newQuantityOfParticipant, newType);

    }
}