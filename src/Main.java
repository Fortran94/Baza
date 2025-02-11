//TODO когда вводишь данные участника он запрашивает второй раз и сохраняет введенные второй раз,
// куда надо переместить строку с созданием нового участника?

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //создаем список
        PatricipantList patricipantList = new PatricipantList();
    while (true) {
        System.out.println("Вы находитесь в главном меню" +
                "\n Выберите пункт меню");
        System.out.println();
        System.out.println("1. Добавить участника " +
                "\n2. Посмотреть список участников");
        Scanner scanner = new Scanner(System.in);
        int menuItem = scanner.nextInt();
        if (menuItem == 1) {
            addPatricipant();
            break;
        }

    }
        patricipantList.list.add(addPatricipant());

        //для теста
        patricipantList.printList();
    }
    public static ParticipantUser addPatricipant() {
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

        return new ParticipantUser(name, surname, callSign, age);
    }
}
