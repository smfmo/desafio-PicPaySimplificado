package com.picpaysimplificado.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record UserRequest(
        @NotNull(message = "Campo obrigatório")
        String firstName,
        @NotNull(message = "Campo obrigatório")
        String lastName,
        String document,
        BigDecimal balance,
        @Email(message = "Insira um Email válido")
        String email,
        @Min(value = 6, message = "Mínimo de 6 caracteres")
        @Max(value = 15, message = "Máximo de 20 carateres")
        String password
) {}
