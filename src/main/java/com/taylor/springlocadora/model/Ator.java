package com.taylor.springlocadora.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

//@Getter  //gera os getters
//@Setter  //gera os setter
@Data  //gera getter, setter e mais umas coisas
@Entity
public class Ator {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_idAtor")
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "atores", fetch = FetchType.EAGER)
    private java.util.List<Titulo> titulos;
}

/* @OneToMany(mappedBy = "ator")
    private java.util.Set<Titulo> atoresTitulos; */
