package com.ProyectoFinalGlobant.GamesStore;

import com.ProyectoFinalGlobant.GamesStore.controllers.ReservationController;
import com.ProyectoFinalGlobant.GamesStore.models.ReservationModel;
import com.ProyectoFinalGlobant.GamesStore.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    private ReservationModel reservation1 = new ReservationModel(1L, 1L, "Juan", "Perez", "mail1@mail.com", "0001");
    private ReservationModel reservation2 = new ReservationModel( 2L, 1L, "John", "Doe", "mail2@mail.com", "0002");
    private ArrayList<ReservationModel> reservationList;

    @BeforeEach
    void fillList() {
        reservationList = new ArrayList<ReservationModel>();
        reservationList.add(reservation1);
        reservationList.add(reservation2);
    }

    @Test
    void shouldGetEmptyReservations() throws Exception {
        given(reservationService.getReservations()).willReturn( new ArrayList<ReservationModel>());
        mockMvc.perform(get("/reservation"))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void shouldGetAllReservations() throws Exception {
        given(reservationService.getReservations()).willReturn(reservationList);
        mockMvc.perform(get("/reservation"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void shouldGetOneReservationByID() throws Exception {
        long reservationId = reservation1.getId();
        given(reservationService.getReservationById(reservation1.getId())).willReturn(java.util.Optional.ofNullable(reservation1));
        mockMvc.perform(get("/reservation/{reservationId}", reservationId))
                .andExpect(jsonPath("$.name", is(reservation1.getName())));
    }

    @Test
    void shouldGetAllReservationsByGameId() throws Exception {
        long gameId = reservation1.getGameId();
        given(reservationService.getReservationByGameId(gameId)).willReturn(reservationList);
        mockMvc.perform(get("/reservation/game/{gameId}", gameId))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void shouldCreateNewReservation() throws Exception {
        given(reservationService.saveReservation(any())).willReturn(reservation1);
        mockMvc.perform(post("/reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"gameId\":1, \"name\":\"Juan\", \"lastName\":\"Perez\", \"documentNumber\":\"0001\", \"email\":\"mail1@mail.com\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Juan")));
    }

    @Test
    void shouldDeleteReservationById() throws Exception {
        long reservationId = reservation1.getId();
        given(reservationService.deleteReservationById(reservationId)).willReturn(true);
        mockMvc.perform(delete("/reservation/{reservationId}", reservationId))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteReservationByGameId() throws Exception {
        long gameId = reservation1.getGameId();
        given(reservationService.deleteReservationByGameId(gameId)).willReturn(1L);
        mockMvc.perform(delete("/reservation/delete/{gameId}", gameId))
                .andExpect(status().isOk());
    }
}
