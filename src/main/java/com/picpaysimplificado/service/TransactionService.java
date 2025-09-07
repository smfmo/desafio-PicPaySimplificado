package com.picpaysimplificado.service;

import com.picpaysimplificado.domain.dto.TransactionRequest;
import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final UserService userService;
    private final RestTemplate restTemplate;

    public TransactionService(TransactionRepository repository,
                              UserService userService,
                              RestTemplate restTemplate) {
        this.repository = repository;
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    public void createTransaction(TransactionRequest request) throws Exception{
        var sender = this.userService.findUserById(request.senderId());
        var receiver = this.userService.findUserById(request.receiverId());

        this.userService.validateTransaction(sender, request.value());

        boolean isAuthorized = this.authorizeTransaction(sender, request.value());
        if (!(isAuthorized)) {
            throw new Exception(
                    "Transação não autorizada!"
            );
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(request.value());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(request.value()));
        receiver.setBalance(receiver.getBalance().add(request.value()));

        this.repository.save(transaction);
        this.userService.save(sender);
        this.userService.save(receiver);
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(
                "https://util.devi.tools/api/v2/authorize",
                Map.class
        );
        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizationResponse.getBody().get("authorization");
            return "true".equals(message);
        } else {
            return false;
        }
    }
}
