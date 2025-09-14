package com.fortran94.bazaweb.repository;

import com.fortran94.bazaweb.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
        //Кастомные запросы
}

