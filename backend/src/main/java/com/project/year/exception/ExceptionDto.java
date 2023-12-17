package com.project.year.exception;

import com.project.year.user.exception.UserErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionDto {
    private UserErrorCode errorCode;
    private String message;
}
