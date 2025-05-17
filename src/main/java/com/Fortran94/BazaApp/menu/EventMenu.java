package com.Fortran94.BazaApp.menu;

import com.Fortran94.BazaApp.dao.EventDAO;
import com.Fortran94.BazaApp.model.Event;
import com.Fortran94.BazaApp.model.ParticipantUser;
import com.Fortran94.BazaApp.service.EventService;
import com.Fortran94.BazaApp.service.ParticipantService;
import com.Fortran94.BazaApp.utils.EventMacker;

import java.util.List;
import java.util.Scanner;

public class EventMenu {

    private final ParticipantService participantService;
    private final EventService eventService;
    private final Scanner scanner;
    private final EventMacker eventMacker;

    public EventMenu(ParticipantService participantService, EventService eventService, Scanner scanner, EventMacker eventMacker) {
        this.participantService = participantService;
        this.eventService = eventService;
        this.scanner = scanner;
        this.eventMacker = eventMacker;
    }


    public void printEventMenu(){
            while (true) {
                System.out.println("1. Добавить мероприятие " +
                        "\n2. Посмотреть список мероприятий" +
                        "\n0. Возврат в главное меню");
                int menuItem = scanner.nextInt();

                if (menuItem == 1) {
                    addEvent();
                } else if (menuItem == 2) {
                    printEventList();
                } else if (menuItem == 0) {
                    break;
                }
            }
    }

    //создание мероприятие
    public void addEvent() {
        Event event = eventMacker.writer();
        eventService.addEvent(event); // добавляем в список
    }

    private void editEvent(Event event) {
        eventMacker.eventEdit(event, eventService);
    } //todo

    public void deleteEvent (Event event) {
        eventService.deleteEvent(event);
        printEventList();
    }

    //todo Сделать обработку если введен пункт которого нет, в М также
    //todo Перенести в сервис
    public int addParticipantToEvent() {
        List<ParticipantUser> participants = this.participantService.getAllParticipants();

        for (int i = 0; i < participants.size(); i++) {
            System.out.println((i + 1) + " " + participants.get(i).getSurname());
        }

        System.out.println("Введите номер участника которого хотите зарегистрировать на это мероприятие");
        int participantId = participants.get(scanner.nextInt() - 1).getId();
        scanner.nextLine(); // Очистка буфера после nextInt()

        return participantId;
    }

    // выводит список мероприятий
    public void printEventList() {
        List<Event> events = this.eventService.getAllEvents();
        while (true) {
            for (int i = 0; i < events.size(); i++) {
                    System.out.println((i + 1) + " " + events.get(i).getName());
            }

            System.out.println("Для просмотра подробной информации о мероприятии введите номер участника" +
                        "\nДля возврата введите 0");
            int point = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после nextInt()

            // Отображает карточку мероприятия, проверяет корректность введенного номера
            if (point > 0 && point <= events.size()) {
                printCard(events, point);
            } else if (point != 0) {
                System.out.println("Нет мероприятия с таким номером!");
            }else {
                break;
            }
        }
    }

    public void printCard(List<Event> events, int point) {
        Event event = events.get(point - 1);
        showEventCard(event);
        handleEventActions(event, events, point, scanner);
    }

    private void showEventCard(Event event) {
        System.out.print("╔══════════════════════════════════════════════╗");
        System.out.println(event.toString());
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    private void handleEventActions(Event event, List<Event> events, int point, Scanner scanner) {

        while (true) {
            System.out.println("1. Редактировать карточку мероприятия" +
                    "\n2. Удалить мероприятие" +
                    "\n3. Записать участника" +
                    "\n4. Посмотреть участников" +
                    "\n0. Возврат к списку");
            int inp = scanner.nextInt();
            scanner.nextLine();

            if (inp == 0) {
                return;
            } else if (inp == 1) {
                editEvent(event);
                events.set(point - 1, eventService.getEventById(event.getId()));
                printCard(events, point);
                return;
            } else if (inp == 2) {
                deleteEvent(event);
            } else if (inp == 3) {
                int participantId = addParticipantToEvent();
                participantService.addParticipantToEvent(participantId, event.getId());
                System.out.println("Участник записан");
            } else if (inp == 4) {
                printEventParticipants(event.getId());
            }else {
                System.out.println("Неверный ввод");
            }
        }
    }

    private void printEventParticipants(int eventId) {
        List<ParticipantUser> participants = eventService.getParticipantsByEvent(eventId);
        System.out.println("Участники этого мероприятия: ");
        System.out.println("╔════════════════════════════════════╗");
        for (int i = 0; i < participants.size(); i++) {
            System.out.println((i + 1) + " " + participants.get(i).getSurname());
        }
        System.out.println("╚════════════════════════════════════╝");
    }

}

