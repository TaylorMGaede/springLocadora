package com.taylor.springlocadora.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taylor.springlocadora.model.Titulo;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {
    @EntityGraph(attributePaths = {"diretor", "classe", "atoresTitulos"})
    List<Titulo> findAll();

    @EntityGraph(attributePaths = {"diretor", "classe", "atoresTitulos"})
    @Override
    Optional<Titulo> findById(Long id);
}
