import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //создаем список
        PatricipantList patricipantList = new PatricipantList();

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

            patricipantList.list.add(new ParticipantUser(name, surname, callSign, age));

            patricipantList.printList();








    }
}
