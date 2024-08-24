package com.ashokit.counsellor.service.portal.dto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ErrorMessageFactory {

    private static final String originator = "counsellor-service";

    public ErrorMessage failure(Exception exception,String errorCode){
        return ErrorMessage.of()
                .messageID(UUID.randomUUID().toString())
                .errorCode(errorCode)
                .messageDateTime(new Date())
                .errorMessage(exception.getMessage())
                .originator(originator)
                .build();
    }
}
