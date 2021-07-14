package com.ProyectoFinalGlobant.GamesStore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GameAlreadyExistException.class)
    public ResponseEntity<?> gameAlreadyExistException(GameAlreadyExistException e, WebRequest r) {

        DetailException detailException = new DetailException(e.getMessage(), e.getGame(), r.getDescription(false), ZonedDateTime.now(ZoneId.systemDefault()));

        return new ResponseEntity<>(detailException, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(GameNotExistException.class)
    public ResponseEntity<?> gameNotExistException(GameNotExistException e, WebRequest r) {

        DetailException detailException = new DetailException(e.getMessage(), "", r.getDescription(false), ZonedDateTime.now(ZoneId.systemDefault()));

        return new ResponseEntity<>(detailException, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(GameBadStatusException.class)
    public ResponseEntity<?> gamBadStatusException(GameBadStatusException e, WebRequest r) {

        DetailException detailException = new DetailException(e.getMessage(), e.getTitle(), r.getDescription(false), ZonedDateTime.now(ZoneId.systemDefault()));

        return new ResponseEntity<>(detailException, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(GameBadRequestException.class)
    public ResponseEntity<?> gameBadRequestException(GameBadRequestException e, WebRequest r) {

        DetailException detailException = new DetailException(e.getMessage(), "", r.getDescription(false), ZonedDateTime.now(ZoneId.systemDefault()));

        return new ResponseEntity<>(detailException, HttpStatus.BAD_REQUEST);
    }

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