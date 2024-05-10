package com.olp.emailservice.controller;

import com.olp.emailservice.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/push")
public class PushNotificationController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/broadcast")
    public ResponseEntity<String> sendBroadcastNotification(@RequestBody Message message) {
        try {
            simpMessagingTemplate.convertAndSend("/all/messages", message);
            return ResponseEntity.ok("Broadcast notification sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send broadcast notification: " + e.getMessage());
        }
    }

    @PostMapping("/private")
    public ResponseEntity<String> sendPrivateNotification(@RequestBody Message message) {
        try {
            simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/specific", message);
            return ResponseEntity.ok("Private notification sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send private notification: " + e.getMessage());
        }
    }
}
