package com.ProyectoFinalGlobant.GamesStore.exceptions;

public class GameBadStatusException extends Exception{

    private String title;

    public GameBadStatusException(String message, String title) {
        super(message);
        this.title=title;
    }

    public String getTitle() {
        return title;
    }
}
