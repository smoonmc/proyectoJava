package com.ProyectoFinalGlobant.GamesStore;
import com.ProyectoFinalGlobant.GamesStore.exceptions.GameBadRequestException;
import com.ProyectoFinalGlobant.GamesStore.exceptions.GameNotExistException;
import com.ProyectoFinalGlobant.GamesStore.models.GameModel;
import com.ProyectoFinalGlobant.GamesStore.repositories.GameRepository;
import com.ProyectoFinalGlobant.GamesStore.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class GameServiceTest {
	@InjectMocks
	private GameService gameService;
	@Mock
	private GameRepository gameRepository;
	private GameModel game1 = new GameModel(1L, "game1", "console1", "2021-07-15", 5L, "AVAILABLE");
	private GameModel game2 = new GameModel(1L, "game1", "console1", "2021-07-15", 5L, "AVAILABLE");
	private ArrayList<GameModel> gameList;
	@BeforeEach
	void fillList() {
		gameService = new GameService();
		gameList = new ArrayList<GameModel>();
		gameList.add(game1);
		gameList.add(game2);
	}
	@Test
	void shouldCreateGame() throws GameBadRequestException {
		Long idBefore = game1.getId();
		MockitoAnnotations.openMocks(this);
		when(gameRepository.findByTitleAndConsole(any(), any())).thenReturn(null);
		when(gameRepository.save(any())).thenReturn(game1);
		gameService.createGame(game1);
		assertEquals(idBefore, game1.getId());
	}
	@Test
	void shouldUpdateGame() throws GameBadRequestException {
		Long idBefore = game1.getId();
		Long number = game1.getCopies();
		MockitoAnnotations.openMocks(this);
		when(gameRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(game1));
		when(gameRepository.save(any())).thenReturn(game1);
		gameService.updateGame(game1, game1.getId());
		assertEquals(idBefore, game1.getId());
	}
	@Test
	void shouldUpdateGameQuantity() throws GameBadRequestException {
		Long idBefore = game1.getId();
		Long copies = 10L;
		MockitoAnnotations.openMocks(this);
		when(gameRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(game1));
		game1.setCopies(copies);
		when(gameRepository.save(any())).thenReturn(game1);
		assertEquals(copies, game1.getCopies());
	}
	@Test
	void shouldDeleteGameGameById() throws GameBadRequestException {
		Long idBefore = game1.getId();
		MockitoAnnotations.openMocks(this);
		when(gameRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(game1));
		doNothing().when(gameRepository).deleteById(game1.getId());
		assertEquals(idBefore,game1.getId());
	}
	@Test
	void shouldGetAllGames() {
		MockitoAnnotations.openMocks(this);
		when(gameRepository.findAll()).thenReturn(gameList);
		assertEquals(gameList.size(),2);
	}
	@Test
	void shouldGetByStatus() throws GameNotExistException {
		MockitoAnnotations.openMocks(this);
		when(gameRepository.findByStatus("AVAILABLE")).thenReturn(gameList);
		assertEquals(gameList.size(),2);
	}
	@Test
	void shouldGetGameById() throws GameNotExistException {
		Long idBefore = game1.getId();
		MockitoAnnotations.openMocks(this);
		when(gameRepository.findById(game1.getId())).thenReturn(java.util.Optional.ofNullable(game1));
		assertEquals(idBefore,game1.getId());
	}

}
