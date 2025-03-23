import java.util.ArrayList;

public class ParticipantList {
   private ArrayList<ParticipantUser> participantList = new ArrayList<>();


//    public void printList() {
//        for (ParticipantUser i : participantList) {
//            System.out.println(i.toString());
//        }
//    }

    //Выводит пофамильный список
    public void printSurnameList() {
        for (int i = 0; i < participantList.size(); i++) {
            System.out.println(i + 1 + " " + participantList.get(i).getSurname());
        }
    }

    public void add(ParticipantUser participant) {
        participantList.add(participant);
    }

    public void edit(int point, ParticipantUser participant) {
        participantList.set(point, participant);
    }

    public ArrayList<ParticipantUser> getParticipantList() {
        return participantList;
    }
}
