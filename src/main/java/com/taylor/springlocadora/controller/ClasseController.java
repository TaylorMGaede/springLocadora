package com.taylor.springlocadora.controller;

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

import com.taylor.springlocadora.model.Classe;
import com.taylor.springlocadora.repository.ClasseRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/classes")
@AllArgsConstructor
public class ClasseController {
    
    private final ClasseRepository classeRepository;


    @GetMapping
    public @ResponseBody java.util.List<Classe> list() {
        return classeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classe> findById(@PathVariable Long id) {
        return classeRepository.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Classe create(@RequestBody Classe classe) {
        return classeRepository.save(classe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classe> update(@PathVariable Long id, @RequestBody Classe classe) {
        return classeRepository.findById(id)
        .map(recordFound -> {
            recordFound.setName(classe.getName());
            recordFound.setValor(classe.getValor());
            recordFound.setDate(classe.getDate());
            Classe updated = classeRepository.save(recordFound);
            return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return classeRepository.findById(id)
        .map(recordFound -> {
            classeRepository.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        })
        .orElse(ResponseEntity.notFound().build());
    }
}