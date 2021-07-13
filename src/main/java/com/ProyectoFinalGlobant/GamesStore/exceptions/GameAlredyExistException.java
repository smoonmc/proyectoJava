package com.ProyectoFinalGlobant.GamesStore.exceptions;

public class GameAlredyExistException extends RuntimeException{

    private String game;

    public GameAlredyExistException(String message, String game) {
        super(message);
        this.game = game;
    }

    public String getGame() {
        return game;
    }
}
