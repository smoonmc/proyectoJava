package com.ProyectoFinalGlobant.GamesStore.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.hibernate.query.criteria.internal.expression.function.CurrentTimeFunction;
import org.springframework.data.auditing.CurrentDateTimeProvider;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reservations")
public class ReservationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(nullable = false)
    private int gameId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(length = 13, nullable = false)
    private String documentNumber;

    @Column(nullable = false)
    private String email;

    @Column(length = 10, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String reservationDate;

    public ReservationModel() {
        this.reservationDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    }

    public ReservationModel(int gameId, String name, String lastName, String email) {
        this.gameId = gameId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.reservationDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationModel)) return false;
        ReservationModel that = (ReservationModel) o;
        return gameId == that.gameId && documentNumber.equals(that.documentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, documentNumber);
    }
}
