package com.picpaysimplificado.domain.transaction;

import jakarta.persistence.*;
import java.util.UUID;

@Entity(name = "transactions")
@Table(name = "transactions",
            schema = "public")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;



    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Transaction other)) {
            return false;
        }
        return this.id.equals(other.id);
    }
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
