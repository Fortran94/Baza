import java.util.ArrayList;

public class ParticipantList {
    ArrayList<ParticipantUser> list = new ArrayList<>();

    public void printList() {
        for (ParticipantUser i : list) {
            System.out.println(i.toString());
        }
    }

    //Выводит пофамильный список
    public void printSurnameList() {
        System.out.println("Для просмотра подробной информации об участнике введите номер участника" +
                "\n Для выхода нажмите 0");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + " " + list.get(i).getSurname());
        }
    }
}
