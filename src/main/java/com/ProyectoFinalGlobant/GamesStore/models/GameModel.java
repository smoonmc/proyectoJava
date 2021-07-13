package com.ProyectoFinalGlobant.GamesStore.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="games")
public class GameModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "console", nullable = false)
    private String console;

    @Column(name = "creationDate", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date creationDate;

    @Column(name = "copies", nullable = false)
    private Long copies;


    @Column(name = "status", nullable = false)
    private String status;

    public GameModel() {
    }

    public GameModel(Long id, String title, String console, Date creationDate, Long copies, String status) {
        this.id = id;
        this.title = title;
        this.console = console;
        this.creationDate = creationDate;
        this.copies = copies;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.toUpperCase();
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console.toUpperCase();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCopies() {
        return copies;
    }

    public void setCopies(Long copies) {
        this.copies = copies;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status.toUpperCase();
    }
}