package com.taylor.springlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taylor.springlocadora.model.Ator;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long>{
    
}
