package com.mycompany.app;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public record CustomerController(CustomerService customerService) {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
        log.info("New customer registration {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);

    }
}
