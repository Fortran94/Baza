package com.fortran94.bazaweb.controller;

import com.fortran94.bazaweb.model.Event;
import com.fortran94.bazaweb.model.ParticipantUser;
import com.fortran94.bazaweb.repository.EventRepository;
import com.fortran94.bazaweb.repository.ParticipantUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/ui")
public class ParticipantController {

    private final ParticipantUserRepository participantUserRepository;
    private final EventRepository eventRepository;

    public ParticipantController(ParticipantUserRepository repository, EventRepository eventRepository){
        this.participantUserRepository = repository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/participants")
    public String showParticipants(Model model) {
        List<ParticipantUser> list = participantUserRepository.findAll();
        model.addAttribute(/*"participants"*/ "participants", list);
        return "participants"; // название шаблона .html
    }

    @GetMapping("/participants/new")
    public String showCreateForm(Model model) {
        model.addAttribute("participant", new ParticipantUser());
        return "participant-form";
    }

    @PostMapping("/participants")
    public String saveParticipant (@ModelAttribute ParticipantUser participant) {
        if (participant.getRegistrationDate() == null) {
            participant.setRegistrationDate(LocalDate.now());
        }

        participantUserRepository.save(participant);
        return "redirect:/ui/participants/" + participant.getId();
    }

    @GetMapping("/participants/{id}")
    public String showCard(@PathVariable Long id, Model model) {
        ParticipantUser participant = participantUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Нет участника с id " + id));
        model.addAttribute("participant", participant);
        return "participant-card";
    }

    @GetMapping("/participants/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ParticipantUser participant = participantUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));
        model.addAttribute("participant", participant);
        return "participant-form";
    }

    @PostMapping("/participants/{id}/delete")
    public String deleteParticipant(@PathVariable Long id) {
        participantUserRepository.deleteById(id);
        return "redirect:/ui/participants";
    }

    @GetMapping("/participants/{id}/assign-event-form")
    public String showEventAssignForm(@PathVariable Long id, Model model) {
        ParticipantUser participant = participantUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Не найден участник с id = " + id));
        List<Event> events = eventRepository.findAll();

        model.addAttribute("participant", participant);
        model.addAttribute("events", events);
        return "assign-event-form";
    }

    @PostMapping("/participants/{id}/assign-event-form")
    public String assignToEvent(@PathVariable Long id, @RequestParam Long eventId) {
        ParticipantUser participant = participantUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Участник не найден"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Мероприятие не найдено"));

        participant.getEvents().add(event);
        event.getParticipants().add(participant); // вот чего не хватало

        participantUserRepository.save(participant);
        eventRepository.save(event); // обязательно

        return "redirect:/ui/participants/" + id;
    }

    @GetMapping("/participants/{id}/events")
    public String showEventsOfParticipant(@PathVariable Long id, Model model) {
        ParticipantUser participantUser = participantUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Участник не найден"));

        model.addAttribute("participant", participantUser);
        model.addAttribute("events", participantUser.getEvents());
        return "participant-events"; // имя Thymeleaf-шаблона
    }

}
