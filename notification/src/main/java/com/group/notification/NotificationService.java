package com.group.notification;

import com.group.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        Notification notification = Notification.builder()
                .message(notificationRequest.getMessage())
                .sender("HeatGroup")
                .sentAt(LocalDateTime.now())
                .toCustomerEmail(notificationRequest.getToCustomerEmail())
                .build();
        notificationRepository.save(notification);
    }
}
