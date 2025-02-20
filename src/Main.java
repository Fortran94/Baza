public class Main {
    public static void main(String[] args) {


        //Создаем список
        ParticipantList participantList = new ParticipantList();
        //Создаем меню
        MainMenu menu = new MainMenu();
        //Отрисовываем меню
        menu.printMainMenu(participantList);

    }
}
