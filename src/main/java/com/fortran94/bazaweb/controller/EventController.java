package com.fortran94.bazaweb.controller;

import com.fortran94.bazaweb.model.Event;
import com.fortran94.bazaweb.model.ParticipantUser;
import com.fortran94.bazaweb.repository.EventRepository;
import com.fortran94.bazaweb.repository.ParticipantUserRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public String saveEvent (@ModelAttribute Event formEvent) {
        if (formEvent.getId() == null) {
            // это новое мероприятие
            formEvent.setType(formEvent.getType().toLowerCase());
            eventRepository.save(formEvent);
        } else {
            // это редактирование
            Event existingEvent = eventRepository.findById(formEvent.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Мероприятие не найдено"));

            existingEvent.setName(formEvent.getName());
            existingEvent.setLocation(formEvent.getLocation());
            existingEvent.setDate(formEvent.getDate());
            existingEvent.setOrganizer(formEvent.getOrganizer());
            existingEvent.setOverview(formEvent.getOverview());
            existingEvent.setQuantityOfParticipant(formEvent.getQuantityOfParticipant());
            existingEvent.setType(formEvent.getType().toLowerCase());
            existingEvent.setResult(formEvent.getResult());

            eventRepository.save(existingEvent); // ✅ participants не тронуты
        }

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
        participant.getEvents().add(event); // обязательно!

        eventRepository.save(event);
        participantUserRepository.save(participant); // тоже обязательно!

        return "redirect:/events/" + id;
    }

    @GetMapping("/{id}/participants")
    public String showParticipantsOfEvent(@PathVariable Long id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Мероприятие не найдено"));

        model.addAttribute("event", event);
        model.addAttribute("participants", event.getParticipants());
        return "event-participants"; // имя Thymeleaf-шаблона
    }

    @GetMapping("/filter")
    public String filterEvents(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Integer minParticipants,
            Model model
    ) {
        List<Event> events = eventRepository.findAll().stream()
                .filter(e -> (type == null || type.isBlank() || e.getType().equalsIgnoreCase(type)))
                .filter(e -> (startDate == null || !e.getDate().isBefore(startDate)))
                .filter(e -> (endDate == null || !e.getDate().isAfter(endDate)))
                .filter(e -> (minParticipants == null || e.getQuantityOfParticipant() >= minParticipants))
                .toList();

        model.addAttribute("events", events);
        model.addAttribute("allParticipants", participantUserRepository.findAll());
        return "events";
    }



}
