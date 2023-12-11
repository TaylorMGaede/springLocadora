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

import com.taylor.springlocadora.model.Ator;
import com.taylor.springlocadora.repository.AtorRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/atores")
@AllArgsConstructor
public class AtorController {
    
    private final AtorRepository atorRepository;
    

    //public CourseController(CourseRepository courseRepository) {
    //    this.courseRepository = courseRepository;
    //}


    @GetMapping
    public @ResponseBody java.util.List<Ator> list() {
        return atorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ator> findById(@PathVariable Long id) {
        return atorRepository.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Ator create(@RequestBody Ator ator) {
        //System.out.println(ator.getName());
        return atorRepository.save(ator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ator> update(@PathVariable Long id, @RequestBody Ator ator) {
        return atorRepository.findById(id)
        .map(recordFound -> {
            recordFound.setName(ator.getName());
            Ator updated = atorRepository.save(recordFound);
            return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return atorRepository.findById(id)
        .map(recordFound -> {
            atorRepository.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        })
        .orElse(ResponseEntity.notFound().build());
    }
}
