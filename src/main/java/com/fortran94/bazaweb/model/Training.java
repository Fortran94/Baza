package com.fortran94.bazaweb.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trainings")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;



    @ManyToMany
    @JoinTable(
            name = "trainings_participants",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    List<ParticipantUser> participants = new ArrayList<>();

    public Training() {
    }

    public Training(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ParticipantUser> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantUser> participants) {
        this.participants = participants;
    }
}
