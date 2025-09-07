package com.picpaysimplificado.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionRequest(
        BigDecimal value,
        UUID senderId,
        UUID receiverId
) {}
