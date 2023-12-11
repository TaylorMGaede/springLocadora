package com.taylor.springlocadora.controller;

import java.util.Set;

import org.hibernate.mapping.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.taylor.springlocadora.model.Ator;
import com.taylor.springlocadora.model.AtorTitulo;
import com.taylor.springlocadora.model.Classe;
import com.taylor.springlocadora.model.Diretor;
import com.taylor.springlocadora.model.Titulo;
import com.taylor.springlocadora.repository.TituloRepository;
import com.taylor.springlocadora.repository.AtorRepository;
import com.taylor.springlocadora.repository.ClasseRepository;
import com.taylor.springlocadora.repository.DiretorRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/titulos")
@AllArgsConstructor
public class TituloController {
    private final TituloRepository tituloRepository;
    private final AtorRepository atorRepository;
    private final DiretorRepository diretorRepository;
    private final ClasseRepository classeRepository;

    @GetMapping
    public @ResponseBody java.util.List<Titulo> list() {
        return tituloRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Titulo findById(@PathVariable Long id) {
        return tituloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Título não encontrado com o ID: " + id));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Titulo create(@RequestBody Titulo titulo) {

        Set<AtorTitulo> atoresTitulos = titulo.getAtoresTitulos();

        if (atoresTitulos != null) {
            for (AtorTitulo atorTitulo : atoresTitulos) {
                Ator ator = atorRepository.findById(atorTitulo.getAtor().getId())
                        .orElseThrow(() -> new RuntimeException("Ator não encontrado"));
                        atorTitulo.setAtor(ator);}}

        Diretor diretor = diretorRepository.findById(titulo.getDiretor().getId())
        .orElseThrow(() -> new RuntimeException("Diretor não encontrado"));

        Classe classe = classeRepository.findById(titulo.getClasse().getId())
        .orElseThrow(() -> new RuntimeException("Classe não encontrada"));

        titulo.setDiretor(diretor);
        titulo.setClasse(classe);

        return tituloRepository.save(titulo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Titulo> update(@PathVariable Long id, @RequestBody Titulo titulo) {
        return tituloRepository.findById(id)
            .map(recordFound -> {
                recordFound.setName(titulo.getName());
                recordFound.setYear(titulo.getYear());
                recordFound.setSynopsis(titulo.getSynopsis());
                recordFound.setCategory(titulo.getCategory());

                Diretor diretor = diretorRepository.findById(titulo.getDiretor().getId())
                        .orElseThrow(() -> new RuntimeException("Diretor não encontrado"));
                recordFound.setDiretor(diretor);

                Classe classe = classeRepository.findById(titulo.getClasse().getId())
                        .orElseThrow(() -> new RuntimeException("Classe não encontrada"));
                recordFound.setClasse(classe);

                Set<AtorTitulo> atoresTitulos = titulo.getAtoresTitulos();
                if (atoresTitulos != null) {
                    for (AtorTitulo atorTitulo : atoresTitulos) {
                        Ator ator = atorRepository.findById(atorTitulo.getAtor().getId())
                                .orElseThrow(() -> new RuntimeException("Ator não encontrado"));
                        atorTitulo.setAtor(ator);
                    }
                    recordFound.setAtoresTitulos(atoresTitulos);
                }

                Titulo updated = tituloRepository.save(recordFound);
                return ResponseEntity.ok().body(updated);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return tituloRepository.findById(id)
            .map(recordFound -> {
                recordFound.getAtoresTitulos().clear();
                recordFound.setDiretor(null);
                recordFound.setClasse(null);
            
                tituloRepository.save(recordFound);
                tituloRepository.deleteById(id);

                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
