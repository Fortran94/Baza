package com.fortran94.bazaweb.controller;

import com.fortran94.bazaweb.model.Event;
import com.fortran94.bazaweb.repository.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventRepository repository;


    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public String showEvents(Model model) {
        List<Event> list = repository.findAll();
        model.addAttribute("events", list);
        return "events"; // название шаблона .html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("event", new Event());
        return "event-form";
    }

    @PostMapping("")
    public String saveEvent (@ModelAttribute Event event) {
        if (event.getType() == null || event.getType().isBlank()) {
            throw new IllegalArgumentException("Тип мероприятия обязателен");
        }
            // Приводим к нижнему регистру
            event.setType(event.getType().toLowerCase());

            if (event.getType().equals("game")) {
                event.setResult(null);
            } else if (event.getType().equals("tournament") && (event.getResult() == null || event.getResult().isBlank())) {
                throw new IllegalArgumentException("Результат обязателен для турнира");
            }
        repository.save(event);
        return "redirect:/events";
    }

    @GetMapping("/{id}")
    public String showCard(@PathVariable Long id, Model model) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Нет мероприятия с id " + id));
        model.addAttribute("event", event);
        return "event-card";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));
        model.addAttribute("event", event);
        return "event-form";
    }

    @PostMapping("/{id}/delete")
    public String deleteEvent(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/events";
    }
}
