package com.Fortran94.BazaApp.menu;

import com.Fortran94.BazaApp.dao.ParticipantDAO;
import com.Fortran94.BazaApp.model.ParticipantUser;
import com.Fortran94.BazaApp.utils.UserMacker;

import java.util.List;
import java.util.Scanner;

public class ParticipantMenu {

    Scanner menuPoint = new Scanner(System.in);


    public void printParticipantMenu(ParticipantDAO participantDAO){
        while (true) {
            System.out.println("1. Добавить участника " +
                    "\n2. Посмотреть список участников" +
                    "\n3. Редактировать участника" +
                    "\n0. Возврат в главное меню");
            int menuItem = menuPoint.nextInt();

            if (menuItem == 1) {
                addParticipant(participantDAO);
            } else if (menuItem == 2) {
                printParticipantList(participantDAO);
            } else if (menuItem == 0) {
                break;
            }
        }
    }

    //создание участника
    public void addParticipant(ParticipantDAO participantDAO) {
        ParticipantUser participant = UserMacker.writer();
        participantDAO.addParticipant(participant); // добавляем в список
    }

    // выводит пофамильный список
    public void printParticipantList(ParticipantDAO participantDAO) {

        List<ParticipantUser> participants = participantDAO.getAllParticipants();
        while (true) {
            for (int i = 0; i < participants.size(); i++) {
                System.out.println((i + 1) + " " + participants.get(i).getSurname());
            }

            System.out.println("Для просмотра подробной информации об участнике введите номер участника" +
                    "\nДля возврата в меню участников введите 0");

            int point = menuPoint.nextInt();

            /* Отображает карточку участника, проверяет корректность введенного номера
             */
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
        while (true) {
            System.out.println(participants.get(point - 1).toString());
            System.out.println("Для возврата к списку нажмите 0");
            point = menuPoint.nextInt();
            if (point == 0) {
                break;
            }
        }
    }
}
