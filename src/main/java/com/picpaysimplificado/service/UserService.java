package com.picpaysimplificado.service;

import com.picpaysimplificado.domain.dto.UserRequest;
import com.picpaysimplificado.domain.dto.UserResponse;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(UserRequest userRequest) {
        User newUser = new User(userRequest);
        this.save(newUser);
        return newUser;
    }

    public void save(User user) {
        this.repository.save(user);
    }

    public User findUserById(UUID id){
        return this.repository.findUserById(id);
    }

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if (sender.getType() == UserType.MERCHANT) {
            throw new Exception(
                    "Usuário do tipo logista não está autorizado a enviar transações."
            );
        }
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception(
                    "Usuário não possui saldo suficiente."
            );
        }
    }
}
