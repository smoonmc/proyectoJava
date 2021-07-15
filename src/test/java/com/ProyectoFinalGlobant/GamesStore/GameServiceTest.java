package com.ProyectoFinalGlobant.GamesStore;

import com.ProyectoFinalGlobant.GamesStore.controllers.GameController;
import com.ProyectoFinalGlobant.GamesStore.exceptions.GameBadRequestException;
import com.ProyectoFinalGlobant.GamesStore.models.GameModel;
import com.ProyectoFinalGlobant.GamesStore.repositories.GameRepository;
import com.ProyectoFinalGlobant.GamesStore.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import	static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
class GameServiceTest {

	@MockBean GameRepository mockGameRepository;

	private GameService gameService = new GameService();

	private GameModel game1 = new GameModel(1L, "game1", "console1", "2021-07-15", 5L, "AVAILABLE");
	private GameModel game2 = new GameModel(1L, "game1", "console1", "2021-07-15", 5L, "AVAILABLE");
	private ArrayList<GameModel> gameList;

	@BeforeEach
	void fillList() {
		gameList = new ArrayList<GameModel>();
		gameList.add(game1);
		gameList.add(game2);
	}

//	@Test
//	void shouldCreateGame() throws GameBadRequestException {
//		Long idBefore = game1.getId();
//		given(mockGameRepository.findByTitleAndConsole(game1.getTitle(), game1.getConsole())).willReturn(null);
//		given(mockGameRepository.save(any())).willReturn(game1);
//		gameService.createGame(game1);
//		assertEquals(idBefore, game1.getId());
//	}
}
