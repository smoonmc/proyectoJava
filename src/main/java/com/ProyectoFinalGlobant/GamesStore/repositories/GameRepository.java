package com.ProyectoFinalGlobant.GamesStore.repositories;

import com.ProyectoFinalGlobant.GamesStore.models.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository  extends JpaRepository <GameModel, Long>  {
    @Query("SELECT b FROM GameModel b WHERE b.status = ?1 ORDER BY b.title" )
    List<GameModel> findByStatus(String status);

   // @Query("SELECT b FROM GameModel b WHERE b.title = ?1 AND b.console = ?2" )
     GameModel findByTitleAndConsole(String title, String console);
}
