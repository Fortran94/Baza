package com.Fortran94.BazaApp.dao;

import com.Fortran94.BazaApp.model.ParticipantUser;
import com.Fortran94.BazaApp.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {

    public void addParticipant(ParticipantUser participant) {
        String sql = "INSERT INTO participants (name, surname, call_sign, age, experience_per_month, number_of_events) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, participant.getName());
            stmt.setString(2, participant.getSurname());
            stmt.setString(3, participant.getCallSign());
            stmt.setInt(4, participant.getAge());
            stmt.setInt(5, participant.getExperiencePerMonth());
            stmt.setInt(6, participant.getNumberOfEvents());
            stmt.executeUpdate();
            System.out.println("Участник добавлен в базу данных!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ParticipantUser> getAllParticipants() {
        List<ParticipantUser> participants = new ArrayList<>();
        String sql = "SELECT * FROM participants";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ParticipantUser participant = new ParticipantUser(
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
