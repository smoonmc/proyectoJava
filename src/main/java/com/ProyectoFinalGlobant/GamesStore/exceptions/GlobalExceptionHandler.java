package com.ProyectoFinalGlobant.GamesStore.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<?> handleBookNotFound (ReservationNotFoundException reservationNotFoundException,
                                                 WebRequest webRequest) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(),
                                                reservationNotFoundException.getMessage(),
                                                webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReservationBadRequestException.class)
    public ResponseEntity<?> handleBookBadRequestException (ReservationBadRequestException reservationBadRequestException,
                                                            WebRequest webRequest) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(),
                reservationBadRequestException.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
