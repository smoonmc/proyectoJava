package com.ProyectoFinalGlobant.GamesStore.exceptions;

import java.time.ZonedDateTime;

public class DetailException {

    private String message;
    private String game;
    private String description;
    private ZonedDateTime dateTime;

    public DetailException(String message, String game, String description, ZonedDateTime dateTime) {
        this.message = message;
        this.game = game;
        this.description = description;
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public String getGame() {
        return game;
    }

    public String getDescription() {
        return description;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }
}
