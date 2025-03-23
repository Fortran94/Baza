import javax.swing.*;
import java.util.Scanner;

public class ParticipantMenu {
    public void printParticipantMenu(ParticipantList participantList){
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
                participantList.add(participant); // добавляем в список
            } else if (menuItem == 2) {
                Scanner pointScanner = new Scanner(System.in);
                //выводит список участников
                while (true) {
                    System.out.println("Для просмотра подробной информации об участнике введите номер участника" + "" +
                            "\n Для выхода нажмите 0");
                    participantList.printSurnameList();
                    int point = pointScanner.nextInt();
                    if (point > 0 && point <= participantList.getParticipantList().size()) {
                        System.out.println(participantList.getParticipantList().get(point - 1).toString());

                    } else if (point != 0) {
                        System.out.println("Нет участника с таким номером!");
                    } else {
                        break;
                    }
                }
            }else if (menuItem == 3) {
                System.out.println("Введите номер участника для редактирования");
                participantList.printSurnameList();
                Scanner editParticipant = new Scanner(System.in);
                int editParticipantPoint = editParticipant.nextInt();
                ParticipantUser participant = UserMacker.writer();
                participantList.edit(editParticipantPoint - 1, participant);
            }
        }
    }
}
