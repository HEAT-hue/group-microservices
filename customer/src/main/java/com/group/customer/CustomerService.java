package com.group.customer;

import com.group.clients.fraud.FraudCheckResponse;
import com.group.clients.fraud.FraudClient;
import com.group.clients.notification.NotificationClient;
import com.group.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder().firstName(request.firstName()).lastName(request.lastName()).email(request.email()).build();

        // TODO: check if email valid
        // TODO: check if email is not taken

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        // todo: make it async. i.e. add to queue
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setToCustomerEmail(customer.getEmail());
        notificationRequest.setToCustomerId(customer.getId());
        notificationRequest.setSentAt(LocalDateTime.now());
        notificationRequest.setMessage(String.format("Hi %s, welcome to HeatGroup", customer.getFirstName()));
        notificationClient.sendNotification(notificationRequest);
    }
}
