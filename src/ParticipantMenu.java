import java.util.Scanner;

public class ParticipantMenu {
    public void printParticipantMenu(ParticipantList participantList){
        Scanner menuPoint = new Scanner(System.in);
        while (true) {
            System.out.println("1. Добавить участника " +
                    "\n2. Посмотреть список участников" +
                    "\n Для возврата введите 0");
            int menuItem = menuPoint.nextInt();

            if (menuItem == 1) {
                //Добавляет нового участника в лист

                participantList.participantList.add(participantList.addParticipant());
                //break;
            } else if (menuItem == 2) {
                Scanner pointScanner = new Scanner(System.in);
                //выводит список участников
                while (true) {
                    participantList.printSurnameList();
                    int point = pointScanner.nextInt();
                    if (point > 0 && point <= participantList.participantList.size()) {
                        System.out.println(participantList.participantList.get(point - 1).toString());
                    } else if (point != 0) {
                        System.out.println("Нет участника с таким номером!");
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
