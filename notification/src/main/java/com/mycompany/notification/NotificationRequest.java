package com.mycompany.notification;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequest{
        private Integer toCustomerId;
        private String toCustomerName;
        private String message;

}