package com.Fortran94.BazaApp.menu;

import com.Fortran94.BazaApp.dao.ParticipantDAO;
import com.Fortran94.BazaApp.model.ParticipantUser;
import com.Fortran94.BazaApp.utils.UserMacker;

import java.util.List;
import java.util.Scanner;

public class ParticipantMenu {

    Scanner menuPoint = new Scanner(System.in);


    public void printParticipantMenu(ParticipantDAO participantDAO){

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

                    }
                /*     Нужен метод который будет редактировать участника
                }else if (menuItem == 3) {
                    System.out.println("Введите номер участника для редактирования");
                    participantDAO.getAllParticipants();
                    Scanner editParticipant = new Scanner(System.in);
                    int editParticipantPoint = editParticipant.nextInt();
                    ParticipantUser participant = UserMacker.writer();
                    participantDAO.edit(editParticipantPoint - 1, participant);
                }*/
    }

    //создание участника
    public void addParticipant(ParticipantDAO participantDAO) {
        ParticipantUser participant = UserMacker.writer();
        participantDAO.addParticipant(participant); // добавляем в список
    }

    // выводит пофамильный список
    public void printParticipantList(ParticipantDAO participantDAO) {
        System.out.println("Для просмотра подробной информации об участнике введите номер участника");

        List<ParticipantUser> participants = participantDAO.getAllParticipants();

        for (int i = 0; i < participants.size(); i++) {
            System.out.println((i + 1) + " " + participants.get(i).getSurname());
        }

        int point = menuPoint.nextInt();
        if (point > 0 && point <= participants.size()) {

                System.out.println(participants.get(point - 1).toString());
                System.out.println("Для возврата к списку нажмите 0");
            //todo тут происходит выход, надо разобраться и сделать так, чтобы не происходил,
            // а продолжал показываться карточка пока пользователь не введет ноль
        } else if (point != 0) {
            System.out.println("Нет участника с таким номером!");
        }
    }
}
