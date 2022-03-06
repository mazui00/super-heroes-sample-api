package com.spring.crud.demo;

import com.spring.crud.demo.model.SuperHero;
import com.spring.crud.demo.repository.SuperHeroRepository;
import com.spring.crud.demo.utils.HelperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


@Slf4j
@SpringBootApplication
@EnableJpaRepositories
public class SuperHeroSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperHeroSampleApplication.class, args);
	}

	@Autowired
	private SuperHeroRepository superHeroRepository;
	
	@Bean
	CommandLineRunner runner() {
		return args -> {

			List<SuperHero> superHeroes = superHeroRepository.findAll();
			if (superHeroes.isEmpty()) {
				log.info("******* Inserting Super heroes to DB *******");
				superHeroRepository.saveAll(HelperUtil.superHeroesSupplier.get());
			} else {
				log.info("******* Super heroes stored in DB Size :: {}", superHeroes.size());
				log.info("******* Super heroes stored in DB :: {}", superHeroes);
			}

		};
	}

}
