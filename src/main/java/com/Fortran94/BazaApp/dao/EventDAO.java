package com.Fortran94.BazaApp.dao;

import com.Fortran94.BazaApp.model.Event;
import com.Fortran94.BazaApp.model.ParticipantUser;
import com.Fortran94.BazaApp.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    Connection conn = DatabaseConnector.connect();

    public void addEvent(Event event) {
        String sql = "INSERT INTO events (name, location, organizer, overview, quantity_of_participants)" +
                " VALUES (?, ?, ?, ?, ?) RETURNING id;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getName());
            stmt.setString(2, event.getLocation());
            stmt.setString(3, event.getOrganizer());
            stmt.setString(4, event.getOverview());
            stmt.setInt(5, event.getQuantityOfParticipantAll());
           // stmt.setInt(6, event.getQuantityOfParticipantOur());

            ResultSet rs = stmt.executeQuery(); // Выполняем запрос и получаем сгенерированный id

            if (rs.next()) {
                event.setId(rs.getInt("id")); // Сохраняем id в объекте
            }

            System.out.println("Мероприятие добавлено в базу данных!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEvent(int id, String newName, String newLocation, String newOrganizer,
                            String newOverview, int newQuantityOfParticipant) {
        String sql = "UPDATE events SET name = ?, location = ?, organizer = ?, overview = ?, " +
                "quantity_of_participants = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setString(2, newLocation);
            stmt.setString(3, newOrganizer);
            stmt.setString(4, newOverview);
            stmt.setInt(5, newQuantityOfParticipant);
            stmt.setInt(6, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Информация о мероприятии успешно обновлена.");
            } else {
                System.out.println("Ошибка: мероприятие не найдено.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEvent(int id) {
        String sql = "DELETE FROM events WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Мероприятие удалено");
            } else {
                System.out.println("Ошибка: мероприятие не найдено.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Event getEventById(int id) {
        String sql = "SELECT * FROM events WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("organizer"),
                        rs.getString("overview"),
                        rs.getInt("quantity_of_participants")
                        //rs.getInt("quantity_of_participants_our")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events ORDER BY id";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("organizer"),
                        rs.getString("overview"),
                        rs.getInt("quantity_of_participants")
                        //rs.getInt("quantity_of_participants_our")
                );
                //participant.setExperiencePerMonth(rs.getInt("experience_per_month"));
                //participant.setNumberOfEvents(rs.getInt("number_of_events"));
                events.add(event);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }


    //Добавляет участников в мероприятие
    public void addParticipantToEvent(int eventId, int participantId) {
        String sql = "INSERT INTO event_participants (participant_id, event_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, participantId);
            stmt.setInt(2, eventId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Получает список участников из мероприятия
    public List<ParticipantUser> getParticipantsByEvent(int eventId) {
        List<ParticipantUser> participantsByEvent = new ArrayList<>();
        String sql = "SELECT p.* FROM participants p JOIN event_participants ep ON p.id = ep.participant_id WHERE ep.event_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, eventId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                participantsByEvent.add(new ParticipantUser(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("call_sign"),
                        rs.getInt("age"),
                        rs.getDate("registration_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participantsByEvent;
    }

}
