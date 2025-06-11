package com.mycompany.clients;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerName,
        String message
) {
}
