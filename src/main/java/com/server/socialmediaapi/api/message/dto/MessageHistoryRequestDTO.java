package com.server.socialmediaapi.api.message.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageHistoryRequestDTO {
    @Pattern(regexp = "(\\d+|null)", message = "The id field has only a numeric value or value \"null\"")
    private Integer id;

    @Pattern(regexp = "\\d+", message = "The sender field has only a numeric value")
    private int sender;

    @Pattern(regexp = "\\d+", message = "The consumer field has only a numeric value")
    private int consumer;
}
