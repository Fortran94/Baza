package com.fortran94.bazaweb.repository;

import com.fortran94.bazaweb.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    //Кастомные запросы
}
