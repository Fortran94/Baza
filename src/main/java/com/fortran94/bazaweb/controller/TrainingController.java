package com.fortran94.bazaweb.controller;

import com.fortran94.bazaweb.model.Event;
import com.fortran94.bazaweb.model.ParticipantUser;
import com.fortran94.bazaweb.model.Training;
import com.fortran94.bazaweb.repository.ParticipantUserRepository;
import com.fortran94.bazaweb.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<Training> trainingList = trainingRepository.findAll();
        model.addAttribute("trainings", trainingList);
//        model.addAttribute("allTrainings", trainingRepository.findAll());
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

    @PostMapping("/{id}/delete")
    public String deleteTraining(@PathVariable Long id, Model model) {

        trainingRepository.deleteById(id);
        return "redirect:/trainings";
    }


    @GetMapping("/{id}/assign-participant")
    public String showAssignParticipantForm(@PathVariable Long id, Model model) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Нет тренировки с id " + id));
        List<ParticipantUser> allParticipants = participantUserRepository.findAll();

        model.addAttribute("event", training);
        model.addAttribute("participants", allParticipants);
        return "assign-participant-form";
    }


}
