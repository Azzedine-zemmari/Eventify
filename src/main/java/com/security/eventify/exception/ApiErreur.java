package com.security.eventify.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiErreur {
    private String exception;
    private LocalDateTime date;
    private int code;
}
