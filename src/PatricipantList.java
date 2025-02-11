import java.util.ArrayList;

public  class PatricipantList {
    ArrayList<ParticipantUser> list = new ArrayList<>();

    public  void printList() {
        for (ParticipantUser i : list) {
            System.out.println(i.toString());
        }
    }

    public  void printSurnameList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + " " +  list.get(i).getSurname());
        }
    }
}
