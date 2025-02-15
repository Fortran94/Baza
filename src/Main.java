public class Main {
    public static void main(String[] args) {
        //Создаем список
        ParticipantList participantList = new ParticipantList();
        //Создаем меню
        Menu menu = new Menu();
        //Отрисовываем меню
        menu.printMenu(participantList);

    }
}
