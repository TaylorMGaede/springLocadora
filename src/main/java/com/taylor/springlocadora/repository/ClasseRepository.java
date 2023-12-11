package com.taylor.springlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taylor.springlocadora.model.Classe;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long>{
    
}
