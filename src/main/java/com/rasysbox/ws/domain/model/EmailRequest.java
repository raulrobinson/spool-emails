package com.rasysbox.ws.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String to;
    private String subject;
    private String body;
}
