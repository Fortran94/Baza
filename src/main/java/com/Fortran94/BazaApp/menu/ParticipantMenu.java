package com.Fortran94.BazaApp.menu;

import com.Fortran94.BazaApp.dao.ParticipantDAO;
import com.Fortran94.BazaApp.model.ParticipantUser;
import com.Fortran94.BazaApp.utils.UserMacker;

import java.util.List;
import java.util.Scanner;

public class ParticipantMenu {
    public void printParticipantMenu(ParticipantDAO participantDAO){
        Scanner menuPoint = new Scanner(System.in);
        while (true) {
            System.out.println("1. Добавить участника " +
                    "\n2. Посмотреть список участников" +
                    "\n3. Редактировать участника" +
                    "\n Для возврата введите 0");
            int menuItem = menuPoint.nextInt();

            if (menuItem == 1) {
                //Добавляет нового участника в лист
                ParticipantUser participant = UserMacker.writer();
                participantDAO.addParticipant(participant); // добавляем в список
            }else if (menuItem == 2) {
                while (true) {
                    System.out.println("Для просмотра подробной информации об участнике введите номер участника" +
                            "\nДля выхода нажмите 0");

                    List<ParticipantUser> participants = participantDAO.getAllParticipants();

                    for (int i = 0; i < participants.size(); i++) {
                        System.out.println((i + 1) + " " + participants.get(i).getSurname());
                    }

                    int point = menuPoint.nextInt();
                    if (point > 0 && point <= participants.size()) {
                        System.out.println(participants.get(point - 1).toString());
                    } else if (point != 0) {
                        System.out.println("Нет участника с таким номером!");
                    } else {
                        break;
                    }
                }
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
    }
}
