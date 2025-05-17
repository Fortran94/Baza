package com.Fortran94.BazaApp.menu;

import com.Fortran94.BazaApp.model.Event;
import com.Fortran94.BazaApp.model.ParticipantUser;
import com.Fortran94.BazaApp.service.EventService;
import com.Fortran94.BazaApp.service.ParticipantService;
import com.Fortran94.BazaApp.utils.UserMacker;

import java.util.List;
import java.util.Scanner;

public class ParticipantMenu {

    private final ParticipantService participantService;
    private final EventService eventService;
    private final Scanner scanner;
    private final UserMacker userMacker;

    public ParticipantMenu(ParticipantService participantService, EventService eventService, Scanner scanner, UserMacker userMacker) {
        this.participantService = participantService;
        this.eventService = eventService;
        this.scanner = scanner;
        this.userMacker = userMacker;
    }

    public void printParticipantMenu(){
        while (true) {
            System.out.println("1. Добавить участника " +
                    "\n2. Посмотреть список участников" +
                    "\n0. Возврат в главное меню");
            int menuItem = scanner.nextInt();

            if (menuItem == 1) {
                addParticipant();
            } else if (menuItem == 2) {
                printParticipantList();
            } else if (menuItem == 0) {
                break;
            }
        }
    }

    /**
     * Вызывает метод создания нового участника, вызывает метод добавляющий участника в БД и
     * передает ему данные только что созданного участника
     */
    public void addParticipant() {
        ParticipantUser participantUser = userMacker.writer();
        participantService.addParticipant(participantUser);
    }

    /**
     * Вызывает метод для редактирования существующего участника и
     * передает ему конкретного участника
     * @param participant
     */
    private void editParticipant(ParticipantUser participant) {
        userMacker.participantEdit(participant, participantService);
    }

    /**
     * Вызывает метод удаления участника из БД,
     * выводит сообщение об успешном удалении
     * @param participant
     */
    public void deleteParticipant (ParticipantUser participant) {
        participantService.deleteParticipant(participant);
        printParticipantList();
    }

    /**
     * /////////
     * @return
     */
    //todo Сделать обработку если введен пункт которого нет, в М также

    public int addParticipantToEvent() {
        List<Event> events = this.eventService.getAllEvents();

        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + " " + events.get(i).getName());
        }

        System.out.println("Введите номер мероприятие на которое хотите зарегистрировать этого участника");

        int eventId = events.get(scanner.nextInt() - 1).getId();
        scanner.nextLine(); // Очистка буфера после nextInt()
        return eventId;
    }


    // выводит пофамильный список
    public void printParticipantList() {

        List<ParticipantUser> participants = participantService.getAllParticipants();
        while (true) {
            for (int i = 0; i < participants.size(); i++) {
                System.out.println((i + 1) + " " + participants.get(i).getSurname());
            }

            System.out.println("Для просмотра подробной информации об участнике введите номер участника" +
                    "\nДля возврата в меню участников введите 0");

            int point = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после nextInt()

            // Отображает карточку участника, проверяет корректность введенного номера
            if (point > 0 && point <= participants.size()) {
                printCard(participants, point);
            } else if (point != 0) {
                System.out.println("Нет участника с таким номером!");
            }else {
                break;
            }
        }
    }

    public void printCard(List<ParticipantUser> participants, int point) {
        ParticipantUser participant = participants.get(point - 1);
        showParticipantCard(participant);
        handleParticipantAction(participant, participants, point, scanner);
    }


    private void showParticipantCard(ParticipantUser participant) {
        int eventCount = participantService.getEventsForParticipant(participant);
        participant.setNumberOfEvents(eventCount);

        System.out.print("╔══════════════════════════════════════════════╗");
        System.out.println(participant);
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    private void handleParticipantAction(ParticipantUser participant, List<ParticipantUser> participants,
                                         int point, Scanner scanner) {

        while (true) {
            System.out.println("1. Редактировать карточку участника" +
                    "\n2. Удалить участника" +
                    "\n3. Записать на мероприятие" +
                    "\n4. Просмотреть мероприятия на которых был участник" +
                    "\n0. Возврат к списку");
            int inp = scanner.nextInt();
            scanner.nextLine();

            if (inp == 0) {
                return;
            }else if (inp == 1) {
                editParticipant(participant);
                participants.set(point - 1, participantService.getParticipantById(participant.getId()));
                printCard(participants, point);
                return;
            } else if (inp == 2) {
                deleteParticipant(participant);
            } else if (inp == 3) {
                int eventId = addParticipantToEvent();
               participantService.addParticipantToEvent(participant.getId(), eventId);
            } else if (inp == 4) {
                printParticipantEvents(participant.getId());

            }else {
                System.out.println("Неверный ввод.");
            }
        }
    }

    private void printParticipantEvents(int participantId) {
        List<Event> events = participantService.getEventsByParticipant(participantId);
        System.out.println("Участник посетил следующие мероприятия: ");
        System.out.println("╔═══════════════════════════════╗");
        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + " " + events.get(i).getName());
        }
        System.out.println("╚═══════════════════════════════╝");
    }

}
