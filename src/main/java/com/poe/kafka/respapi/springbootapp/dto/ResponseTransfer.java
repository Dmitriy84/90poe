package com.poe.kafka.respapi.springbootapp.dto;

import lombok.*;

@AllArgsConstructor
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ResponseTransfer {
    private String message;
}
