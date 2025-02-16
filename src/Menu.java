/*
Класс меню с методами отрисовки меню, выбора пункта меню.
 */
import java.util.Scanner;
public class Menu {
    public void printMenu(ParticipantList participantList) {
        Logo logo = new Logo();
        logo.printLogo();
        //Бесконечный цикл меню
        while (true) {
            System.out.println("Вы находитесь в главном меню" +
                    "\n Выберите пункт меню");
            System.out.println();
            System.out.println("1. Добавить участника " +
                    "\n2. Посмотреть список участников" +
                    "\n3. Посмотреть список участников по фамилиями" +
                    "\n4. Редактировать участника");
            Scanner scanner = new Scanner(System.in);
            int menuItem = scanner.nextInt();
            if (menuItem == 1) {
                //Добавляет нового участника в лист
                participantList.list.add(addParticipant());
                //break;
            } else if (menuItem == 2) {
                participantList.printList();
            } else if (menuItem == 3) {
                participantList.printSurnameList();
                participantSelect(participantList);
            } else if (menuItem == 4) {
                System.out.println("Введите номер участника для редактирования");
                participantList.printSurnameList();
                Scanner editParticipant = new Scanner(System.in);
                int editParticipantPoint = editParticipant.nextInt();
                editParticipant(participantList, editParticipantPoint);
            }
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
    //Показывает подробную информацию об участнике из пофамильного списка
    public void participantSelect(ParticipantList participantList) {
        Scanner pointScanner = new Scanner(System.in);
        while (true) {
            int point = pointScanner.nextInt();
            if (point != 0) {
                System.out.println(participantList.list.get(point - 1).toString());
            } else{
                break;
            }

        }
    }

    //todo Исправить ошибку при редактировании участника
    //Редактирует выбранного участника
    public void editParticipant(ParticipantList participantList, int point) {
        System.out.println("Текущая информация:");
        participantList.list.get(point - 1).toString();
        Scanner input = new Scanner(System.in);
        System.out.print("Введите имя: ");
        participantList.list.get(point).setName(input.nextLine());
        System.out.println();
        System.out.print("Введите фамилию: ");
        participantList.list.get(point).setSurname(input.nextLine());
        System.out.println();
        System.out.print("Введите позывной: ");
        participantList.list.get(point).setCallSign(input.nextLine());
        System.out.println();
        System.out.print("Введите возраст: ");
        participantList.list.get(point).setAge(input.nextInt());
        System.out.println();
        System.out.println("Участник успешно изменен");
        System.out.println();
        System.out.println("Обновленная информация:");
        participantList.list.get(point).toString();
    }
}
