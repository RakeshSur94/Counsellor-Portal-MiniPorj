package com.ashokit.counsellor.service.portal.exception.handler;

import com.ashokit.counsellor.service.portal.dto.ErrorMessage;
import com.ashokit.counsellor.service.portal.dto.ErrorMessageFactory;
import com.ashokit.counsellor.service.portal.exception.AccountAlreadyRegisterException;
import com.ashokit.counsellor.service.portal.exception.AccountNotRegisterException;
import com.ashokit.counsellor.service.portal.exception.CounsellorDoesNotExistException;
import com.ashokit.counsellor.service.portal.exception.EmailOrPasswordMissMatchException;
import com.ashokit.counsellor.service.portal.utils.ErrorCodes;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
@AllArgsConstructor
public class ErrorMessageExceptionHandler {
    private final ErrorMessageFactory errorMessageFactory;

    @ExceptionHandler(AccountAlreadyRegisterException.class)
    public ResponseEntity<ErrorMessage> accountAlreadyRegisterException(AccountAlreadyRegisterException e, HttpServletRequest request){
        log.error(request.getRequestURL().toString(),e);
        return ResponseEntity.status(HttpStatus.GONE).body(errorMessageFactory.failure(e, ErrorCodes.USER_ALREADY_ACTIVATED));
    }
    @ExceptionHandler(EmailOrPasswordMissMatchException.class)
    public ResponseEntity<ErrorMessage> emailOrPasswordMissMatchException(EmailOrPasswordMissMatchException e, HttpServletRequest request){
        log.error(request.getRequestURL().toString(),e);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorMessageFactory.failure(e,ErrorCodes.EMAIL_PASSWORD_MISS_MATCH));
    }
    @ExceptionHandler(AccountNotRegisterException.class)
    public ResponseEntity<ErrorMessage> accountNotRegisterException(AccountNotRegisterException e, HttpServletRequest request){
        log.error(request.getRequestURL().toString(),e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessageFactory.failure(e,ErrorCodes.EMAIL_PASSWORD_MISS_MATCH));
    }
    @ExceptionHandler(CounsellorDoesNotExistException.class)
    public ResponseEntity<ErrorMessage> unknownException(CounsellorDoesNotExistException e,HttpServletRequest request){
        log.error(request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageFactory.failure(e,ErrorCodes.COUNSELLOR_NOT_EXIST));
    }
}
