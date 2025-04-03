/*package com.Fortran94.BazaApp.menu;

import com.Fortran94.BazaApp.dao.ParticipantDAO;
import com.Fortran94.BazaApp.model.Event;
import com.Fortran94.BazaApp.model.ParticipantUser;

import java.util.List;
import java.util.Scanner;

public class EventMenu {

    Scanner menuPoint = new Scanner(System.in);

    private final EventDAO eventDAO;


    public EventMenu(ParticipantDAO participantDAO) {
        this.eventDAO = eventDAO;
    }

    public void printEventMenu(EventDAO eventDAO){
            while (true) {
                System.out.println("1. Добавить мероприятие " +
                        "\n2. Посмотреть список мероприятий" +
                        "\n0. Возврат в главное меню");
                int menuItem = menuPoint.nextInt();

                if (menuItem == 1) {
                    //
                } else if (menuItem == 2) {
                    //
                } else if (menuItem == 0) {
                    break;
                }
            }
        }

        //создание мероприятие
        public void addEvent(EventDAO eventDAO) {
            //
        }

        private void editEvent(EventDAO eventDAO) {
        //
        }

        public void deleteParticipant (Event event) {
        //
        }


        // выводит список мероприятий
        public void printEventList(EventDAO eventDAO) {

            List<ParticipantUser> participants = this.eventDAO.getAllEvents();
            while (true) {
                for (int i = 0; i < participants.size(); i++) {
                    System.out.println((i + 1) + " " + participants.get(i).getSurname());
                }

                System.out.println("Для просмотра подробной информации об участнике введите номер участника" +
                        "\nДля возврата в меню участников введите 0");

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
            System.out.println(events.get(point - 1).toString());
            Scanner input = new Scanner(System.in);
            while (true) {
                System.out.println("1. Редактировать карточку участника" +
                        "\n2. Удалить участника" +
                        "\n0. Возврат к списку");
                int inp = input.nextInt();
                input.nextLine(); // Очищаем буфер после nextInt()
                if (inp == 0) {
                    return;
                } else if (inp == 1) {
                    editEvent(events.get(point - 1));
                    events.set(point - 1, eventDAO.getParticipantById(events.get(point - 1).getId()));
                    printCard(events, point);
                    return;
                }else if (inp == 2) {
                    deleteParticipant(events.get(point - 1));
                }

            }
        }
    }

}
*/