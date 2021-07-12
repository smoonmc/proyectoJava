package com.ProyectoFinalGlobant.GamesStore.services;

import com.ProyectoFinalGlobant.GamesStore.models.GameModel;
import com.ProyectoFinalGlobant.GamesStore.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;


    public List<GameModel> getAllGames(){

        return gameRepository.findAll(Sort.by("titulo").ascending());


    }


}
