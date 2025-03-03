import Resurses.Logo;

import java.util.Scanner;

public class MainMenu {
    public void printFirstMenu (ParticipantList participantList) {
        /*
         * Рисуем лого и выводим пункты меню
         */
        Logo logo = new Logo();
        logo.printLogo();

        while (true) {
            System.out.println("Вы находитесь в главном меню" +
                    "\n Выберите пункт меню");
            System.out.println();
            System.out.println("1. Участники" +
                    "\n2. Мероприятия" +
                    "\nДля выхода нажмите 0");

            Scanner scanner = new Scanner(System.in);
            int menuItem = scanner.nextInt();
            if (menuItem == 1) {
                ParticipantMenu participantMenu = new ParticipantMenu();
                participantMenu.printParticipantMenu(participantList);
            }else if (menuItem == 2) {
                // todo пункт с мероприятиями
            }else if (menuItem == 0){
            System.exit(0);
            }

        }
    }
}
