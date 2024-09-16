package com.group.notification;

import com.group.clients.notification.NotificationRequest;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;

    @Observed(
            name = "user.name",
            contextualName = "Send notification",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
    @PostMapping("notification")
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
        log.info("new notification...{}", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
