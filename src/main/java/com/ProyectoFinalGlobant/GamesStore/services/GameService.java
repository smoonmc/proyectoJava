package com.ProyectoFinalGlobant.GamesStore.services;

import com.ProyectoFinalGlobant.GamesStore.exceptions.GameAlreadyExistException;
import com.ProyectoFinalGlobant.GamesStore.exceptions.GameNotExistException;
import com.ProyectoFinalGlobant.GamesStore.models.GameModel;
import com.ProyectoFinalGlobant.GamesStore.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    //POST CREATE GAMES
    public void createGame(@RequestBody GameModel game) throws GameAlreadyExistException {

        GameModel  gameModel= gameRepository.findByTitleAndConsole(game.getTitle().toUpperCase(),game.getConsole().toUpperCase());

        if(gameModel != null ){
            throw new GameAlreadyExistException ("ALERT: Game already exist in database", game.getTitle());
        }
        game.setTitle(game.getTitle().toUpperCase());
        game.setConsole(game.getConsole().toUpperCase());
        game.setStatus(game.getStatus().toUpperCase());
        gameRepository.save(game);

    }

    //UPDATE GAME
    public void updateGame(GameModel game, @PathVariable("id") Long id) {

            Long  number= game.getCopies();

            if( number <= 0) {
                game.setStatus("RESERVED");
            }else{
                game.setStatus("AVAILABLE");
            }

            game.setId(id);
            gameRepository.save(game);
    }

    //DELETE BY ID
    public void deleteGameById(@PathVariable("id") Long id) throws GameNotExistException {
        Optional<GameModel> gameOptional = gameRepository.findById(id);

        if(gameOptional.isEmpty()){
            throw new GameNotExistException("ALERT: Game not exist in database!");
        }

        gameRepository.deleteById(id);

    }

    //GET ALL GAMES
    public List<GameModel> getAllGames() {

        return gameRepository.findAll(Sort.by("title").ascending());
    }

    //GET GAME BY STATUS
    public List<GameModel> getByStatus(@PathVariable("status") String status) throws GameNotExistException{

        List<GameModel> gameStatus = gameRepository.findByStatus(status);

        if (gameStatus.isEmpty()){
            throw new GameNotExistException("ALERT: Games whit status:" +status+ " not exist in Database!");
        }

        return gameRepository.findByStatus(status);

    }

    //GET GAME BY ID
    public GameModel getGameById(@PathVariable("id") Long id) throws GameNotExistException  {
        Optional<GameModel> gameOptional = gameRepository.findById(id);

        if(gameOptional.isEmpty()){
            throw new GameNotExistException("ALERT: Game Id:" +id+ " not exist in Database!");
        }

       return gameOptional.get();
    }

}
