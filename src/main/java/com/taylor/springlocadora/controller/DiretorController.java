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

import com.taylor.springlocadora.model.Diretor;
import com.taylor.springlocadora.repository.DiretorRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/diretores")
@AllArgsConstructor
public class DiretorController {
    
    private final DiretorRepository diretorRepository;
    

    //public CourseController(CourseRepository courseRepository) {
    //    this.courseRepository = courseRepository;
    //}


    @GetMapping
    public @ResponseBody java.util.List<Diretor> list() {
        return diretorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diretor> findById(@PathVariable Long id) {
        return diretorRepository.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Diretor create(@RequestBody Diretor diretor) {
        return diretorRepository.save(diretor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diretor> update(@PathVariable Long id, @RequestBody Diretor diretor) {
        return diretorRepository.findById(id)
        .map(recordFound -> {
            recordFound.setName(diretor.getName());
            Diretor updated = diretorRepository.save(recordFound);
            return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return diretorRepository.findById(id)
        .map(recordFound -> {
            diretorRepository.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        })
        .orElse(ResponseEntity.notFound().build());
    }
}
