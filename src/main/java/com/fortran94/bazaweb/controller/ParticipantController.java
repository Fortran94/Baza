package com.fortran94.bazaweb.controller;

import com.fortran94.bazaweb.model.ParticipantUser;
import com.fortran94.bazaweb.repository.ParticipantUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/ui")
public class ParticipantController {

    private final ParticipantUserRepository repository;

    public ParticipantController(ParticipantUserRepository repository){
        this.repository = repository;
    }

    @GetMapping("/participants")
    public String showParticipants(Model model) {
        List<ParticipantUser> list = repository.findAll();
        model.addAttribute("participants", list);
        return "participants"; // название шаблона .html
    }

    @GetMapping("/participants/new")
    public String showCreateForm(Model model) {
        model.addAttribute("participant", new ParticipantUser());
        return "participant-form";
    }

    @PostMapping("/participants")
    public String saveParticipant (@ModelAttribute ParticipantUser participant) {
        repository.save(participant);
        return "redirect:/ui/participants";
    }




}
