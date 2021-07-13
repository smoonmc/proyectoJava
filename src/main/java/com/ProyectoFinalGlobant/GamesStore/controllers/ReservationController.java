package com.ProyectoFinalGlobant.GamesStore.Controllers;

import com.ProyectoFinalGlobant.GamesStore.Models.ReservationModel;
import com.ProyectoFinalGlobant.GamesStore.Services.ReservationService;
import com.ProyectoFinalGlobant.GamesStore.Exceptions.ReservationBadRequestException;
import com.ProyectoFinalGlobant.GamesStore.Exceptions.ReservationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping
    public ArrayList<ReservationModel> getReservations() {
        return reservationService.getReservations();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ReservationModel saveReservation(@RequestBody ReservationModel reservation) throws ReservationBadRequestException {
        if ((reservation.getGameId() == 0) ||
                (reservation.getDocumentNumber() == null) ||
                (reservation.getName() == null) ||
                (reservation.getLastName() == null) ||
                (reservation.getEmail() == null)){
            throw new ReservationBadRequestException("Invalid field on creation request");
        }
        try {
            return reservationService.saveReservation(reservation);
        } catch (DataIntegrityViolationException e) {
            throw new ReservationBadRequestException("User " + reservation.getDocumentNumber() + " has already reserved game " + reservation.getGameId());
        }
    }

    @PostMapping(path ="{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public ReservationModel updateReservation(@RequestBody ReservationModel reservation, @PathVariable("id") Integer id)
            throws ReservationBadRequestException {
        if ((reservation.getGameId() == 0) ||
                (reservation.getDocumentNumber() == null) ||
                (reservation.getName() == null) ||
                (reservation.getLastName() == null) ||
                (reservation.getEmail() == null)){
            throw new ReservationBadRequestException("Invalid field on creation request");
        }
        try {
            return reservationService.updateReservation(reservation, id);
        } catch (DataIntegrityViolationException e) {
            throw new ReservationBadRequestException("User " + reservation.getDocumentNumber() + " has already reserved game " + reservation.getGameId());
        }
    }

    @GetMapping(path = "/{id}")
    public Optional<ReservationModel> getReservationsById(@PathVariable("id") Integer id) throws ReservationNotFoundException {
        Optional<ReservationModel> response = reservationService.getReservationById(id);
        if (response.isEmpty()) {
            throw new ReservationNotFoundException("Reservation " + id + " does not exist");
        }
        return response;
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Integer id) throws ReservationNotFoundException {
        boolean ok = this.reservationService.deleteReservation(id);
        if (ok) {
            return "Reservation " + id + " has been eliminated";
        } else {
            throw new ReservationNotFoundException("Reservation " + id + " does not exist");
        }
    }
}