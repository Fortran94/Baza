package com.fortran94.bazaweb.controller;

import com.fortran94.bazaweb.model.Event;
import com.fortran94.bazaweb.repository.EventRepository;
import com.fortran94.bazaweb.repository.ParticipantUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Comparator;


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
        Event closestEvent = getClosestUpcomingEvent();
        model.addAttribute("closestEvent", closestEvent);
        model.addAttribute("participants", participantUserRepository.findAll());
        model.addAttribute("events", eventRepository.findAll());
        return "index";
    }




    public Event getClosestUpcomingEvent() {
        return eventRepository.findAll().stream()
                .filter(e -> e.getDate().isAfter(LocalDate.now()))
                .min(Comparator.comparing(Event::getDate))
                .orElse(null);
    }




}
