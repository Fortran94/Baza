package com.fortran94.bazaweb.controller;

import com.fortran94.bazaweb.repository.EventRepository;
import com.fortran94.bazaweb.repository.ParticipantUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Scanner;

@Controller
public class HomeController {

    private final ParticipantUserRepository participantUserRepository;
    private final EventRepository eventRepository;

    public HomeController(ParticipantUserRepository participantUserRepository, EventRepository eventRepository) {
        this.participantUserRepository = participantUserRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("participants", participantUserRepository.findAll());
        model.addAttribute("events", eventRepository.findAll());
        return "index";
    }



}
