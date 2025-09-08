package com.picpaysimplificado.service;

import com.picpaysimplificado.domain.dto.NotificationRequest;
import com.picpaysimplificado.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class NotificationService {

    private final RestTemplate restTemplate;

    public NotificationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendNotification(User user, String message) throws Exception{
        String email = user.getEmail();
        NotificationRequest request = new NotificationRequest(email, message);
        ResponseEntity<String> notificationResponse = restTemplate.postForEntity(
                "https://util.devi.tools/api/v2/authorize",
                request,
                String.class
        );
        if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
            log.error("Erro ao enviar notificação");
            throw new Exception("Serviço de notificação está fora do ar!");
        }
    }
}
