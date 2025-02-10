import java.util.ArrayList;

public class PatricipantList {
    ArrayList<ParticipantUser> list = new ArrayList<>();

    public void printList() {
        for (ParticipantUser i : list) {
            System.out.println(i.toString());
        }
    }
}
