package com.mycompany.customer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequest {
        private Integer toCustomerId;
        private String toCustomerName;
        private String message;

}