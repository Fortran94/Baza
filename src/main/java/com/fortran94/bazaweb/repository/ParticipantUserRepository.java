package com.fortran94.bazaweb.repository;

import com.fortran94.bazaweb.model.ParticipantUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantUserRepository extends JpaRepository<ParticipantUser, Long> {
    // кастомные запросы
}
