package com.mycompany.customer;

import com.mycompany.clients.FraudCheckResponse;
import com.mycompany.clients.FraudClient;
import com.mycompany.clients.NotificationClient;
import com.mycompany.clients.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //todo check if email valid
        //todo check if email not taken
        customerRepository.saveAndFlush(customer);
        //todo check if fraudster
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        notificationClient.sendNotification(new NotificationRequest(customer.getId(), customer.getFirstName()+customer.getLastName(), "Account created"));

    }
}
