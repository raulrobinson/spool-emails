package com.rasysbox.ws.domain.model;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailResponse {
    private String message;
    private String status;
    private Timestamp timestamp;

    public EmailResponse(String message, String status) {
        this.message = message;
        this.status = status;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}
