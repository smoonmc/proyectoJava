package com.ProyectoFinalGlobant.GamesStore.Repositories;

import com.ProyectoFinalGlobant.GamesStore.Models.ReservationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationModel, Integer> {
    public abstract ArrayList<ReservationModel> findByDocumentNumberAndGameId(String documentNumber, Integer gameID);
}
