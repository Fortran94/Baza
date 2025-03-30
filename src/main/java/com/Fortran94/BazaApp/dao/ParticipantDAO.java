package com.Fortran94.BazaApp.dao;

import com.Fortran94.BazaApp.model.ParticipantUser;
import com.Fortran94.BazaApp.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {

    public void addParticipant(ParticipantUser participant) {
        String sql = "INSERT INTO participants (name, surname, call_sign, age, experience_per_month, number_of_events)" +
                " VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, participant.getName());
            stmt.setString(2, participant.getSurname());
            stmt.setString(3, participant.getCallSign());
            stmt.setInt(4, participant.getAge());
            stmt.setInt(5, participant.getExperiencePerMonth());
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

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ParticipantUser(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("call_sign"),
                        rs.getInt("age")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<ParticipantUser> getAllParticipants() {
        List<ParticipantUser> participants = new ArrayList<>();
        String sql = "SELECT * FROM participants ORDER BY id";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ParticipantUser participant = new ParticipantUser(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("call_sign"),
                        rs.getInt("age")
                );
                participant.setExperiencePerMonth(rs.getInt("experience_per_month"));
                participant.setNumberOfEvents(rs.getInt("number_of_events"));
                participants.add(participant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }

}
