package org.rail.project.config;

import org.rail.project.model.Character;
import org.rail.project.repository.CharacterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner commandLineRunner(CharacterRepository characterRepository) {
        return args -> {
            Character character = new Character(1L, "Isaac", new BigDecimal("3.50"), 6,
                    new BigDecimal("1.0"), new BigDecimal(0));
            Character character2 = new Character(2L, "Magdalene", new BigDecimal("3.50"), 8,
                    new BigDecimal("0.85"), new BigDecimal(0));
            Character character3 = new Character(3L, "Judas", new BigDecimal("4.72"), 2,
                    new BigDecimal("1.0"), new BigDecimal(0));
            characterRepository.save(character);
            characterRepository.save(character2);
            characterRepository.save(character3);
        };
    }
}

