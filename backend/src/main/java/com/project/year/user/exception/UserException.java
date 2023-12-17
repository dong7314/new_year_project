package com.project.year.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserException extends RuntimeException {
    private UserErrorCode errorCode;
    private String message;
}
