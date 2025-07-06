package com.fortran94.bazaweb.controller;

import com.fortran94.bazaweb.model.Event;
import com.fortran94.bazaweb.model.ParticipantUser;
import com.fortran94.bazaweb.repository.EventRepository;
import com.fortran94.bazaweb.repository.ParticipantUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventRepository eventRepository;
    private final ParticipantUserRepository participantUserRepository;


    public EventController(EventRepository eventRepository, ParticipantUserRepository participantUserRepository) {
        this.eventRepository = eventRepository;
        this.participantUserRepository = participantUserRepository;
    }

    @GetMapping("")
    public String showEvents(Model model) {
        List<Event> list = eventRepository.findAll();
        model.addAttribute("events", list);
        model.addAttribute("allParticipants", participantUserRepository.findAll());
        return "events"; // название шаблона .html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("allParticipants", participantUserRepository.findAll());
        return "event-form";
    }

    @PostMapping("")
    public String saveEvent (@ModelAttribute Event event) {
        if (event.getType() == null || event.getType().isBlank()) {
            throw new IllegalArgumentException("Тип мероприятия обязателен");
        }
        // Приводим к нижнему регистру
        event.setType(event.getType().toLowerCase());

        eventRepository.save(event);
        return "redirect:/events";
    }

    @GetMapping("/{id}")
    public String showCard(@PathVariable Long id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Нет мероприятия с id " + id));
        model.addAttribute("event", event);
        return "event-card";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));
        model.addAttribute("event", event);
        model.addAttribute("allParticipants", participantUserRepository.findAll());
        return "event-form";
    }

    @PostMapping("/{id}/delete")
    public String deleteEvent(@PathVariable Long id) {
        eventRepository.deleteById(id);
        return "redirect:/events";
    }

    @GetMapping("/{id}/assign-participant")
    public String showAssignParticipantForm(@PathVariable Long id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Нет мероприятия с id " + id));
        List<ParticipantUser> allParticipants = participantUserRepository.findAll();

        model.addAttribute("event", event);
        model.addAttribute("participants", allParticipants);
        return "assign-participant-form";
    }

    @PostMapping("/{id}/assign-participant")
    public String assignParticipant(@PathVariable Long id, @RequestParam Long participantId) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Мероприятие не найдено"));
        ParticipantUser participant = participantUserRepository.findById(participantId)
                .orElseThrow(() -> new IllegalArgumentException("Участник не найден"));

        event.getParticipants().add(participant);
        eventRepository.save(event);
        return "redirect:/events/" + id + "/edit";
    }

    @GetMapping("/{id}/participants")
    public String showParticipantsOfEvent(@PathVariable Long id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Мероприятие не найдено"));

        model.addAttribute("event", event);
        model.addAttribute("participants", event.getParticipants());
        return "event-participants"; // имя Thymeleaf-шаблона
    }


}
