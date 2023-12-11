package com.taylor.springlocadora;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.taylor.springlocadora.model.Ator;
import com.taylor.springlocadora.repository.AtorRepository;
import com.taylor.springlocadora.repository.ClasseRepository;
import com.taylor.springlocadora.model.Diretor;
import com.taylor.springlocadora.repository.DiretorRepository;
import com.taylor.springlocadora.model.Classe;
import com.taylor.springlocadora.repository.ClasseRepository;
import com.taylor.springlocadora.model.Titulo;
import com.taylor.springlocadora.repository.TituloRepository;

@SpringBootApplication
public class SpringLocadoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLocadoraApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(AtorRepository atorRepository, DiretorRepository diretorRepository, ClasseRepository classeRepository, TituloRepository tituloRepository) {
		return args -> {
			atorRepository.deleteAll();
			diretorRepository.deleteAll();
			classeRepository.deleteAll();
			tituloRepository.deleteAll();

			Ator c = new Ator();
			c.setName("É o fulano");

			atorRepository.save(c);
		};
	}
}
