package com.Fortran94.BazaApp.dao;

import com.Fortran94.BazaApp.model.ParticipantUser;
import com.Fortran94.BazaApp.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {

    Connection conn = DatabaseConnector.connect();

    public void addParticipant(ParticipantUser participant) {
        String sql = "INSERT INTO participants (name, surname, call_sign, age, registration_date, number_of_events)" +
                " VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, participant.getName());
            stmt.setString(2, participant.getSurname());
            stmt.setString(3, participant.getCallSign());
            stmt.setInt(4, participant.getAge());
            stmt.setDate(5, participant.getRegistrationDate());
            stmt.setInt(6, participant.getNumberOfEvents());

            ResultSet rs = stmt.executeQuery(); // Выполняем запрос и получаем сгенерированный id

            if (rs.next()) {
                participant.setId(rs.getInt("id")); // Сохраняем id в объекте
            }

            System.out.println("Участник добавлен в базу данных!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateParticipant(int id, String newName, String newSurname, String newCallSign, int newAge) {
        String sql = "UPDATE participants SET name = ?, surname = ?, call_sign = ?, age = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setString(2, newSurname);
            stmt.setString(3, newCallSign);
            stmt.setInt(4, newAge);
            stmt.setInt(5, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Участник успешно обновлён.");
            } else {
                System.out.println("Ошибка: участник не найден.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteParticipant(int id) {
        String sql = "DELETE FROM participants WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Черт изгнан!");
            } else {
                System.out.println("Ошибка: участник не найден.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ParticipantUser getParticipantById(int id) {
        String sql = "SELECT * FROM participants WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ParticipantUser(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("call_sign"),
                        rs.getInt("age"),
                        rs.getDate("registration_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<ParticipantUser> getAllParticipants() {
        List<ParticipantUser> participants = new ArrayList<>();
        String sql = "SELECT * FROM participants ORDER BY id";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ParticipantUser participant = new ParticipantUser(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("call_sign"),
                        rs.getInt("age"),
                        rs.getDate("registration_date")
                );
                //participant.setExperiencePerMonth(rs.getInt("experience_per_month"));
                participant.setNumberOfEvents(rs.getInt("number_of_events"));
                participants.add(participant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }
    //Добавляет участников в мероприятие
    public void addParticipantToEvent(int participantId, int eventId) {
        String sql = "INSERT INTO event_participants (participant_id, event_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
             stmt.setInt(1, participantId);
             stmt.setInt(2, eventId);
             stmt.executeUpdate();
            System.out.println("Участник записан на мероприятие");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /// Нужно ли оно тут?
    //Получает список участников из мероприятия
    //todo сделать чтоб возвращал только ид и фамилию
    public List<ParticipantUser> getParticipantsByEvent(int eventId) {
        List<ParticipantUser> participants = new ArrayList<>();
        String sql = "SELECT p.* FROM participants p JOIN event_participants ep ON p.id = ep.participant_id WHERE ep.event_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, eventId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                participants.add(new ParticipantUser(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("callSign"),
                        rs.getInt("age"),
                        rs.getDate("registration_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }

    //todo исправить
    /// Получает список мероприятий у участника
   /* public List<Event> getEventsByParticipant(int participantId) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT e.* FROM events e JOIN event_participants ep ON e.id = ep.event_id WHERE ep.participant_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, participantId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                events.add(new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("organizer"),
                        rs.getString("overview"),
                        rs.getInt("quantity_of_participants")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
*/





}
