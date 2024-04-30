package com.noob.lms.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException{

    public ConflictException(String message ){
        super(message);
    }
}
