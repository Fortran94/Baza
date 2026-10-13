package com.fortran94.bazaweb.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class TelegramRequestDto {

    @JsonProperty("chat_id")
    Long chatId;
    @JsonProperty("text")
    String message;

}
