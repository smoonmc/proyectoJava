package com.ProyectoFinalGlobant.GamesStore.controllers;

import com.ProyectoFinalGlobant.GamesStore.models.GameModel;
import com.ProyectoFinalGlobant.GamesStore.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

   @Autowired
    private GameService gameService;

   @GetMapping("/create")
   public ResponseEntity<Void> createGames(@ResponseBody){

   }

   @GetMapping("/getAll")
    public List<GameModel> getAllGames(){
       return gameService.getAllGames();
   }

}
