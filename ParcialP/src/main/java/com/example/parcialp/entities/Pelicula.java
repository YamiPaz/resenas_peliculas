package com.example.parcialp.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pelicula")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Pelicula extends Base {

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "reseña", length = 2000)
    private String resena;

    @Column(name = "calificacion")
    private int calificacion;
}
