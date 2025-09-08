package com.picpaysimplificado.domain.dto;

public record NotificationRequest(
        String email,
        String message
) {
}
