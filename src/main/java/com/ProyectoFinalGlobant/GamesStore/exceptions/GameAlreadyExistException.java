package com.ProyectoFinalGlobant.GamesStore.exceptions;

public class GameAlreadyExistException extends RuntimeException{

    private String game;

    public GameAlreadyExistException(String message, String game) {
        super(message);
        this.game = game;
    }

    public String getGame() {
        return game;
    }
}
