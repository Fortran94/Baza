package com.fortran94.bazaweb.controller;

import com.fortran94.bazaweb.model.ParticipantUser;
import com.fortran94.bazaweb.repository.ParticipantUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/participants")
public class ParticipantController {

    private final ParticipantUserRepository repository;

    public ParticipantController(ParticipantUserRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<ParticipantUser> getAllParticipants() {
        return repository.findAll();
    }


}
