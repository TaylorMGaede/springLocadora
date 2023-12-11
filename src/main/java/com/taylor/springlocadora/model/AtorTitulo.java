package com.taylor.springlocadora.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class AtorTitulo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_idAtorTitulo")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "_idAtor")
    private Ator ator;

    @ManyToOne
    @JoinColumn(name = "_idTitulo")
    private Titulo titulo;
}
