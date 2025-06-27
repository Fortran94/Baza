package com.fortran94.bazaweb.controller;

import com.fortran94.bazaweb.model.ParticipantUser;
import com.fortran94.bazaweb.repository.ParticipantUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/participants/{id}")
    public String showCard(@PathVariable Long id, Model model) {
        ParticipantUser participant = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Нет участника с id " + id));
        model.addAttribute("participant", participant);
        return "participant-card";
    }

    @GetMapping("/participants/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ParticipantUser participant = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));
        model.addAttribute("participant", participant);
        return "participant-form";
    }






}
