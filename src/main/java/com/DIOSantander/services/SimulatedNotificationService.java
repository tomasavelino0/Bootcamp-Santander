package com.DIOSantander.services;

import com.DIOSantander.Dtos.NotificationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SimulatedNotificationService {
  public ResponseEntity<String> sendNotification(NotificationDTO notificationDTO) {
    System.out.println(notificationDTO.message());
    return new ResponseEntity<>(HttpStatus.OK);
  }
}