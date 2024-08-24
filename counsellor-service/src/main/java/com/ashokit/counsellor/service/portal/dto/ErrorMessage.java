package com.ashokit.counsellor.service.portal.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@ToString
@Getter
@Builder(builderMethodName = "of")
public class ErrorMessage {
    private final String messageID;
    private final String errorCode;
    private final Date messageDateTime;
    private final String errorMessage;
    private final String originator;
}
