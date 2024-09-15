package com.group.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void saveNotification(NotificationRequest notificationRequest) {
        Notification notification = Notification.builder()
                .notification(notificationRequest.notification)
                .build();
        notificationRepository.save(notification);
    }
}
