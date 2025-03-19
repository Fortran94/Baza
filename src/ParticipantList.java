import java.util.ArrayList;
import java.util.Scanner;

public class ParticipantList {
    ArrayList<ParticipantUser> participantList = new ArrayList<>();

    public void printList() {
        for (ParticipantUser i : participantList) {
            System.out.println(i.toString());
        }
    }

    //Выводит пофамильный список
    public void printSurnameList() {
        System.out.println("Для просмотра подробной информации об участнике введите номер участника" +
                "\n Для выхода нажмите 0");
        for (int i = 0; i < participantList.size(); i++) {
            System.out.println(i + 1 + " " + participantList.get(i).getSurname());
        }
    }

    //Метод создает нового участника
    public ParticipantUser addParticipant() {
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
        System.out.println();
        System.out.println("Участник успешно добавлен");
        System.out.println();
        return new ParticipantUser(name, surname, callSign, age);
    }
}
