package com.ProyectoFinalGlobant.GamesStore.services;

import com.ProyectoFinalGlobant.GamesStore.exceptions.ReservationBadRequestException;
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

    public ArrayList<ReservationModel> getReservations() {
        return (ArrayList<ReservationModel>) reservationRepository.findAll();
    }

    public Optional<ReservationModel> getReservationById(Integer id) {
        return reservationRepository.findById(id);
    }

    public ArrayList<ReservationModel> getReservationByGameId(Integer gameId) {
        return (ArrayList<ReservationModel>) reservationRepository.findByGameId(gameId);
    }

    public ReservationModel saveReservation(ReservationModel reservation) throws ReservationBadRequestException {
        reservation.setDocumentNumber(fixDocumentNumber(reservation.getDocumentNumber()));
        ArrayList<ReservationModel> findings = reservationRepository.findByDocumentNumberAndGameId(reservation.getDocumentNumber(), reservation.getId());
        if (findings.isEmpty()) {
            reservation.setName(reservation.getName().toUpperCase());
            reservation.setLastName(reservation.getLastName().toUpperCase());
            reservation.setEmail(reservation.getEmail().toUpperCase());
            return reservationRepository.save(reservation);
        }
        else {
            throw new ReservationBadRequestException("Game already reserved by user");
        }
    }

    public ReservationModel updateReservation(ReservationModel reservation, Integer id) {
        reservation.setId(id);
        reservation.setName(reservation.getName().toUpperCase());
        reservation.setLastName(reservation.getLastName().toUpperCase());
        reservation.setEmail(reservation.getEmail().toUpperCase());
        reservation.setDocumentNumber(fixDocumentNumber(reservation.getDocumentNumber()));
        return reservationRepository.save(reservation);
    }

    public boolean deleteReservationById(Integer id) {
        try {
            reservationRepository.deleteById(id);
            return true;
        }catch(Exception err) {
            return false;
        }
    }

    public long deleteReservationByGameId(Integer gameId) {
        ArrayList<ReservationModel> reservations = reservationRepository.findByGameId(gameId);
        if (reservations.isEmpty()) return 0;
        else {
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
