import java.util.Scanner;

public class ParticipantMenu {
    public void printParticipantMenu(ParticipantList participantList){
        while (true) {
            System.out.println("1. Добавить участника " +
                    "\n2. Посмотреть список участников" +
                    "\n Для возврата введите 0");
            Scanner menuPoint = new Scanner(System.in);
            int menuItem = menuPoint.nextInt();

            if (menuItem == 1) {
                //Добавляет нового участника в лист
                participantList.list.add(participantList.addParticipant());
                //break;
            } else if (menuItem == 2) {
                //выводит список участников
                while (true) {
                    participantList.printSurnameList();
                    Scanner pointScanner = new Scanner(System.in);

                    int point = pointScanner.nextInt();
                    if (point != 0) {
                        System.out.println(participantList.list.get(point - 1).toString());
                    } else {
                        break;
                    }

                }

            }
        }
    }
}
