package com.picpaysimplificado.domain.transaction;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "transaction",
            schema = "public")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
