package com.ProyectoFinalGlobant.GamesStore.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="games")
public class GameModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="titulo", nullable = false)
    private String titulo;


    @Column(name="consola", nullable = false)
    private String consola;

    @Column(name="fecha_creacion", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fecha_creacion;

    @Column(name="copias", nullable = false)
    private Long copias;


    @Column(name="estado", nullable = false)
    private String estado;

    public GameModel(){ }

    public GameModel(Long id, String titulo, String consola, Date fecha_creacion, Long copias, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.consola = consola;
        this.fecha_creacion = fecha_creacion;
        this.copias = copias;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Long getCopias() {
        return copias;
    }

    public void setCopias(Long copias) {
        this.copias = copias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
