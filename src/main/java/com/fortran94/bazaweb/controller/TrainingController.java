package com.fortran94.bazaweb.controller;

import com.fortran94.bazaweb.model.Training;
import com.fortran94.bazaweb.repository.ParticipantUserRepository;
import com.fortran94.bazaweb.repository.TrainingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/trainings")
public class TrainingController {

    private final ParticipantUserRepository participantUserRepository;
    private final TrainingRepository trainingRepository;

    public TrainingController (ParticipantUserRepository participantUserRepository, TrainingRepository trainingRepository){
        this.trainingRepository = trainingRepository;
        this.participantUserRepository = participantUserRepository;
    }

    @GetMapping("")
    public String showTrainings(Model model) {
        List<Training> list = trainingRepository.findAll();
        model.addAttribute("trainings", list);
        model.addAttribute("allTrainings", trainingRepository.findAll());
        return "trainings"; // название шаблона
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("training", new Training());
        model.addAttribute("allParticipants", participantUserRepository.findAll());
        return "training-form";
    }

    @PostMapping("")
    public String saveTraining(@ModelAttribute Training formTraining) {
        trainingRepository.save(formTraining);
        return "redirect:/trainings";
    }

    @GetMapping("/{id}")
    public String showTraining(@PathVariable Long id, Model model) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Нет тренировки с id " + id));
        model.addAttribute("training", training);
        return "training-card";
    }
}
