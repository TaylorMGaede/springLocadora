package com.taylor.springlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taylor.springlocadora.model.Diretor;

@Repository
public interface DiretorRepository extends JpaRepository<Diretor, Long>{
    
}