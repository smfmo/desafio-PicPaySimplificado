package com.picpaysimplificado.domain.dto;

import java.math.BigDecimal;

public record UserResponse(
        String firstName,
        String lastName,
        String document,
        BigDecimal balance,
        String email
) {}
