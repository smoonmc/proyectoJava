package com.ProyectoFinalGlobant.GamesStore.repositories;

import com.ProyectoFinalGlobant.GamesStore.models.ReservationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationModel, Long> {
    public abstract ArrayList<ReservationModel> findByDocumentNumberAndGameId(String documentNumber, Long gameID);
    public abstract ArrayList<ReservationModel> findByGameId(Long gameID);
}