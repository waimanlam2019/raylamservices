package com.mycompany.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
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
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}", FraudCheckResponse.class, customer.getId());

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        //todo send notification
        NotificationRequest notificationRequest = new NotificationRequest(customer.getId(), customer.getFirstName()+customer.getLastName(), "Account created");
        restTemplate.postForObject("http://NOTIFICATION/api/v1/notification/", notificationRequest, Void.class);


    }
}
