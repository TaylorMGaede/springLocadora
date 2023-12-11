package com.taylor.springlocadora.model;

import java.util.Set;

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

//@Getter  //gera os getters
//@Setter  //gera os setter
@Data  //gera getter, setter e mais umas coisas
@Entity
public class Diretor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_idDiretor")
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @OneToMany(mappedBy = "diretor")
    private Set<Titulo> diretoresTitulos;
}