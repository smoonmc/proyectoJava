package com.ProyectoFinalGlobant.GamesStore.Services;

import com.ProyectoFinalGlobant.GamesStore.Models.ReservationModel;
import com.ProyectoFinalGlobant.GamesStore.Repositories.ReservationRepository;
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

    public ArrayList<ReservationModel> getReservationByDocumentNumberAndGameId(String documentNumber, Integer gameId) {
        return (ArrayList<ReservationModel>) reservationRepository.findByDocumentNumberAndGameId(documentNumber, gameId);
    }

    public ReservationModel saveReservation(ReservationModel reservation) {
        reservation.setName(reservation.getName().toUpperCase());
        reservation.setLastName(reservation.getLastName().toUpperCase());
        reservation.setEmail(reservation.getEmail().toUpperCase());
        reservation.setDocumentNumber(fixDocumentNumber(reservation.getDocumentNumber()));
        return reservationRepository.save(reservation);
    }

    public ReservationModel updateReservation(ReservationModel reservation, Integer id) {
        reservation.setId(id);
        reservation.setName(reservation.getName().toUpperCase());
        reservation.setLastName(reservation.getLastName().toUpperCase());
        reservation.setEmail(reservation.getEmail().toUpperCase());
        reservation.setDocumentNumber(fixDocumentNumber(reservation.getDocumentNumber()));
        return reservationRepository.save(reservation);
    }

    public boolean deleteReservation(Integer id) {
        try {
            reservationRepository.deleteById(id);
            return true;
        }catch(Exception err) {
            return false;
        }
    }

    public String fixDocumentNumber(String documentNumber) {
        return documentNumber.replace(".", "")
                .replace("-", "")
                .toUpperCase();
    }
}
