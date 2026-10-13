package com.fortran94.bazaweb.service;

import com.fortran94.bazaweb.model.dto.EventNotificationDto;
import com.fortran94.bazaweb.model.dto.TelegramRequestDto;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.format.DateTimeFormatter;

@Service
public class NotificationService {


    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;
    private final String token = "7324080199:AAHvVmUF_I7hT_ESOnUJvmBtSkEVTHnxLA4";
    private final Long chatId = 1649828488L;


    public NotificationService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private String convertRequest (TelegramRequestDto telegramRequestDto) {
        return objectMapper.writeValueAsString(telegramRequestDto);
    }

    private HttpRequest buildRequest(String json) {
        return HttpRequest.newBuilder()
                .uri(URI.create("https://api.telegram.org/bot" + token + "/sendMessage"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    private String formatMessage(EventNotificationDto event) {
        return """
                🎯 Запланировано мероприятие!

                🏷 Тип: %s
                📌 Название: %s
                📍 Место: %s
                👤 Организатор: %s
                📅 Дата: %s

                📝 Описание:
                %s
                """.formatted(
                translateType(event.type()),
                event.name(),
                event.location(),
                event.organizer(),
                event.date().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                event.overview()
        );
    }

    private String translateType(String type) {
        return switch (type.toLowerCase()) {
            case "game" -> "Игра";
            case "tournament" -> "Турнир";
            default -> type;
        };
    }

    public void sendEventNotification (EventNotificationDto eventNotification) {
        String message = formatMessage(eventNotification);
        TelegramRequestDto requestDto = TelegramRequestDto.builder()
                .chatId(chatId)
                .message(message)
                .build();
        String json = convertRequest(requestDto);

        try {
            HttpResponse<String> response = httpClient.send(buildRequest(json), HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }








}
