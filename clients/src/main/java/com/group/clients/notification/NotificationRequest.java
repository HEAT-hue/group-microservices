package com.group.clients.notification;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class NotificationRequest {

    private String message;

    private LocalDateTime sentAt;

    private String toCustomerEmail;

    private Integer toCustomerId;
}
