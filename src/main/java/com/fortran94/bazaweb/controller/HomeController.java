package com.fortran94.bazaweb.controller;

import com.fortran94.bazaweb.repository.ParticipantUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Scanner;

@Controller
public class HomeController {

    private final ParticipantUserRepository participantUserRepository;

    public HomeController(ParticipantUserRepository participantUserRepository) {
        this.participantUserRepository = participantUserRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("participants", participantUserRepository.findAll());
        return "index";
    }


}
