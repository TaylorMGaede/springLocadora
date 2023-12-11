package com.taylor.springlocadora.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Titulo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_idTitulo")
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(nullable = false)
    private Long year;

    @Column(length = 500, nullable = false)
    private String synopsis;

    @Column(length = 200, nullable = false)
    private String category;

    @OneToMany(mappedBy = "titulo")
    private java.util.Set<AtorTitulo> atoresTitulos;

    @ManyToOne
    @JoinColumn(name = "_idDiretor")
    private Diretor diretor;

    @ManyToOne
    @JoinColumn(name = "_idClasse")
    private Classe classe;
}
