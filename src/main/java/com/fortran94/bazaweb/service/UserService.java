package com.fortran94.bazaweb.service;


import com.fortran94.bazaweb.model.ParticipantUser;

import java.util.List;

public interface UserService {

    List<ParticipantUser> getAllParticipants();

    ParticipantUser getParticipantById(Long id);

    void addParticipant(ParticipantUser participantUser);

    void deleteParticipant(Long id);


}
