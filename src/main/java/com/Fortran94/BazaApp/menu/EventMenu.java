package com.Fortran94.BazaApp.menu;

import com.Fortran94.BazaApp.dao.EventDAO;
import com.Fortran94.BazaApp.dao.ParticipantDAO;
import com.Fortran94.BazaApp.model.Event;
import com.Fortran94.BazaApp.model.ParticipantUser;
import com.Fortran94.BazaApp.utils.EventMacker;
import com.Fortran94.BazaApp.model.ParticipantUser;

import java.util.List;
import java.util.Scanner;

public class EventMenu {

    Scanner menuPoint = new Scanner(System.in);
    ParticipantDAO participantDAO = new ParticipantDAO();
    private final EventDAO eventDAO;


    public EventMenu(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public void printEventMenu(EventDAO eventDAO){
            while (true) {
                System.out.println("1. Добавить мероприятие " +
                        "\n2. Посмотреть список мероприятий" +
                        "\n0. Возврат в главное меню");
                int menuItem = menuPoint.nextInt();

                if (menuItem == 1) {
                    addEvent(this.eventDAO);
                } else if (menuItem == 2) {
                    printEventList(eventDAO);
                } else if (menuItem == 0) {
                    break;
                }
            }
    }

    //создание мероприятие
    public void addEvent(EventDAO eventDAO) {
        Event event = EventMacker.writer();
        eventDAO.addEvent(event); // добавляем в список
    }

    private void editEvent(Event event) {
        EventMacker.eventEdit(event, eventDAO);
    }

    public void deleteEvent (Event event) {
        eventDAO.deleteEvent(event.getId());
        printEventList(eventDAO);
    }

    public int addParticipantToEvent() {
        List<ParticipantUser> participants = this.participantDAO.getAllParticipants();

        for (int i = 0; i < participants.size(); i++) {
            System.out.println((i + 1) + " " + participants.get(i).getSurname());
        }

        System.out.println("Введите номер участника которого хотите зарегистрировать на это мероприятие");
        int participantId = participants.get(menuPoint.nextInt() - 1).getId();
        menuPoint.nextLine(); // Очистка буфера после nextInt()

        return participantId;
    }

    // выводит список мероприятий
    public void printEventList(EventDAO eventDAO) {
        List<Event> events = this.eventDAO.getAllEvents();
        while (true) {
            for (int i = 0; i < events.size(); i++) {
                    System.out.println((i + 1) + " " + events.get(i).getName());
            }

            System.out.println("Для просмотра подробной информации о мероприятии введите номер участника" +
                        "\nДля возврата введите 0");
            int point = menuPoint.nextInt();
            menuPoint.nextLine(); // Очистка буфера после nextInt()

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
        handleEventActions(event, events, point);
    }

    private void showEventCard(Event event) {
        System.out.print("╔══════════════════════════════════════════════╗");
        System.out.println(event.toString());
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    private void handleEventActions(Event event, List<Event> events, int point) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("1. Редактировать карточку мероприятия" +
                    "\n2. Удалить мероприятие" +
                    "\n3. Записать участника" +
                    "\n4. Посмотреть участников" +
                    "\n0. Возврат к списку");
            int inp = input.nextInt();
            input.nextLine();

            if (inp == 0) {
                return;
            } else if (inp == 1) {
                editEvent(event);
                events.set(point - 1, eventDAO.getEventById(event.getId()));
                printCard(events, point);
                return;
            } else if (inp == 2) {
                deleteEvent(event);
            } else if (inp == 3) {
                int participantId = addParticipantToEvent();
                participantDAO.addParticipantToEvent(participantId, event.getId());
                System.out.println("Участник записан");
            } else if (inp == 4) {
                printEventParticipants(event.getId());
            }else {
                System.out.println("Неверный ввод");
            }
        }
    }

    private void printEventParticipants(int eventId) {
        List<ParticipantUser> participants = eventDAO.getParticipantsByEvent(eventId);
        System.out.println("Участники этого мероприятия: ");
        System.out.println("╔════════════════════════════════════╗");
        for (int i = 0; i < participants.size(); i++) {
            System.out.println((i + 1) + " " + participants.get(i).getSurname());
        }
        System.out.println("╚════════════════════════════════════╝");
    }

}

