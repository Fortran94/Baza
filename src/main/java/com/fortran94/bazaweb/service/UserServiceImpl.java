package com.fortran94.bazaweb.service;

import com.fortran94.bazaweb.model.ParticipantUser;
import com.fortran94.bazaweb.repository.ParticipantUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{


    private final ParticipantUserRepository repository;

    public UserServiceImpl(ParticipantUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ParticipantUser> getAllParticipants() {
        return repository.findAll();
    }

    @Override
    public ParticipantUser getParticipantById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Участник не найден"));
    }

    @Override
    public void addParticipant(ParticipantUser participantUser) {
        repository.save(participantUser);
    }

    @Override
    public void deleteParticipant(Long id) {
        repository.deleteById(id);
    }
}
