package com.ProyectoFinalGlobant.GamesStore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GameAlredyExistException.class)
    public ResponseEntity<?> gameAlreadyExistException(GameAlredyExistException e, WebRequest r){

        DetailException detailException = new DetailException(e.getMessage(),e.getGame(),r.getDescription(false), ZonedDateTime.now(ZoneId.systemDefault()));

      return new  ResponseEntity<>(detailException, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(GameNotExistException.class)
    public ResponseEntity<?> gameNotExistException(GameNotExistException e, WebRequest r){

        DetailException detailException = new DetailException(e.getMessage(),"", r.getDescription(false), ZonedDateTime.now(ZoneId.systemDefault()));

        return new  ResponseEntity<>(detailException, HttpStatus.BAD_REQUEST);

    }




}
