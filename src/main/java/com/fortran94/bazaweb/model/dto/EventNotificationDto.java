package com.fortran94.bazaweb.model.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EventNotificationDto(
        String name,
        String location,
        String organizer,
        String overview,
        String type,
        LocalDate date
) {
}