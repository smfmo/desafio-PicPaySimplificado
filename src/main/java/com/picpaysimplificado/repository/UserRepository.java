package com.picpaysimplificado.repository;

import com.picpaysimplificado.domain.user.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByDocument(String document);
    User findUserById(UUID id);
}
