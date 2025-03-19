public class ParticipantUser extends User {
    private int experiencePerMonth = 1;
    private int numberOfEvents;


    public ParticipantUser(String name, String surname, String callSign, int age) {
        super(name, surname, callSign, age);
    }

    public int getExperiencePerMonth() {
        return experiencePerMonth;
    }

    public void setExperiencePerMonth(int experiencePerMonth) {
        this.experiencePerMonth = experiencePerMonth;
    }

    public int getNumberOfEvents() {
        return numberOfEvents;
    }

    public void setNumberOfEvents(int numberOfEvents) {
        this.numberOfEvents = numberOfEvents;
    }

    private double activityLevel() {//todo переименовать лист
        return Math.round(((double) this.numberOfEvents / this.experiencePerMonth) * 100.0) / 100.0;
    }

    public void takePart() {
        numberOfEvents++;
    }

    //TODO сделать абстрактный метод
    @Override
    public String toString() {
        return
                "\n Имя: " + getName() +
                        "\n Фамилия: " + getSurname() +
                        "\n Позывной: " + getCallSign() +
                        "\n Возраст: " + getAge() +
                        "\n Стаж участника в месяцах: " + getExperiencePerMonth() +
                        "\n Количество посещенных мероприятия: " + getNumberOfEvents() +
                        "\n Уровень активности: " + activityLevel() +
                        "\n ";
    }
}
