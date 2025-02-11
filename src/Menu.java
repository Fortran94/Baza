/*
Класс меню с методами отрисовки меню, выбора пункта меню.
 */
import java.util.Scanner;
public class Menu {
    public void printMenu(PatricipantList patricipantList) {
        //Бесконечный цикл меню
        while (true) {
            System.out.println("Вы находитесь в главном меню" +
                    "\n Выберите пункт меню");
            System.out.println();
            System.out.println("1. Добавить участника " +
                    "\n2. Посмотреть список участников" +
                    "\n3. Посмотреть список участников по фамилиями");
            Scanner scanner = new Scanner(System.in);
            int menuItem = scanner.nextInt();
            if (menuItem == 1) {
                //Добавляет нового участника в лист
                patricipantList.list.add(addPatricipant());
                //break;
            } else if (menuItem == 2) {
                patricipantList.printList();
            } else if (menuItem == 3) {
                System.out.println("Для просмотра подробной информации об участнике введите номер участника");
                patricipantList.printSurnameList();
                patricipantSelect(patricipantList);
            }

        }
    }

    //Метод создает нового участника
    public ParticipantUser addPatricipant() {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = input.nextLine();
        System.out.println();
        System.out.print("Введите фамилию: ");
        String surname = input.nextLine();
        System.out.println();
        System.out.print("Введите позывной: ");
        String callSign = input.nextLine();
        System.out.println();
        System.out.print("Введите возраст: ");
        int age = input.nextInt();
        System.out.println("Участник успешно добавлен");
        return new ParticipantUser(name, surname, callSign, age);
    }

    //todo Здесь баг, не выводит информацию о выбранном участнике
    public void patricipantSelect(PatricipantList patricipantList) {
        Scanner pointScanner = new Scanner(System.in);
        int point = pointScanner.nextInt();
        patricipantList.list.get(point - 1).toString();


    }
}
