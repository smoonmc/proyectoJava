package com.ProyectoFinalGlobant.GamesStore.services;

import com.ProyectoFinalGlobant.GamesStore.exceptions.GameBadStatusException;
import com.ProyectoFinalGlobant.GamesStore.exceptions.ReservationBadRequestException;
import com.ProyectoFinalGlobant.GamesStore.exceptions.ReservationNotFoundException;
import com.ProyectoFinalGlobant.GamesStore.models.ReservationModel;
import com.ProyectoFinalGlobant.GamesStore.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    GameService gameService;

    public ArrayList<ReservationModel> getReservations() {
        return (ArrayList<ReservationModel>) reservationRepository.findAll();
    }

    public Optional<ReservationModel> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public ArrayList<ReservationModel> getReservationByGameId(Long gameId) {
        return (ArrayList<ReservationModel>) reservationRepository.findByGameId(gameId);
    }

    public ReservationModel saveReservation(ReservationModel reservation) throws ReservationBadRequestException, GameBadStatusException {
        reservation.setDocumentNumber(fixDocumentNumber(reservation.getDocumentNumber()));
        ArrayList<ReservationModel> findings = reservationRepository.findByDocumentNumberAndGameId(reservation.getDocumentNumber(), reservation.getGameId());
        if (findings.isEmpty()) {
            reservation.setName(reservation.getName().toUpperCase());
            reservation.setLastName(reservation.getLastName().toUpperCase());
            reservation.setEmail(reservation.getEmail().toUpperCase());
            ReservationModel response = reservationRepository.save(reservation);
            gameService.updateGameQuantity(reservation.getGameId(),-1);
            return response;
        }else {
            throw new ReservationBadRequestException("Game already reserved by user");
        }
    }

    public ReservationModel updateReservation(ReservationModel reservation, Long id) throws ReservationNotFoundException {
        Optional<ReservationModel> originalReservation = reservationRepository.findById(id);
        if (originalReservation.isEmpty()){
            throw new ReservationNotFoundException("Reservation does not exist, sorry.");
        }else{
        reservation.setId(id);
        reservation.setName(reservation.getName().toUpperCase());
        reservation.setLastName(reservation.getLastName().toUpperCase());
        reservation.setEmail(reservation.getEmail().toUpperCase());
        reservation.setDocumentNumber(originalReservation.get().getDocumentNumber());
        reservation.setGameId(originalReservation.get().getGameId());
        return reservationRepository.save(reservation);
        }
    }

    public boolean deleteReservationById(Long id) throws ReservationNotFoundException {
        Optional<ReservationModel> reservation = reservationRepository.findById(id);
        if (reservation.isEmpty()){
            throw new ReservationNotFoundException("Reservation does not exist");
        }else {
            try {
                gameService.updateGameQuantity(reservation.get().getGameId(), 1);
                reservationRepository.deleteById(id);
                return true;
            }catch(Exception err) {
                return false;
            }
        }

    }

    public long deleteReservationByGameId(Long gameId) throws GameBadStatusException {
        ArrayList<ReservationModel> reservations = reservationRepository.findByGameId(gameId);
        if (reservations.isEmpty()) return 0;
        else {
            gameService.updateGameQuantity((long) gameId, reservations.size());
            reservations.stream().forEach(eachReservation -> reservationRepository.deleteById(eachReservation.getId()));
            return reservations.size();
        }
    }

    public String fixDocumentNumber(String documentNumber) {
        return documentNumber.replace(".", "")
                .replace("-", "")
                .toUpperCase();
    }
}
