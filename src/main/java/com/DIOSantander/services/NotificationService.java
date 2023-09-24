package com.DIOSantander.services;

import com.DIOSantander.Dtos.NotificationDTO;
import com.DIOSantander.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

  @Autowired
  private SimulatedNotificationService simulatedNotificationService;

  public void sendNotification(User user, String message) throws Exception {
    String email = user.getEmail();
    NotificationDTO notificationRequest = new NotificationDTO(email, message);

    ResponseEntity<String> notificationResponse = simulatedNotificationService.sendNotification(
        notificationRequest);

    if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
      throw new Exception("Servico esta fora do ar!");
    }
  }
}