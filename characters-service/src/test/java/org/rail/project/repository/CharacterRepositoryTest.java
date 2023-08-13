package org.rail.project.repository;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.rail.project.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@Testcontainers
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = NONE)
@ContextConfiguration(initializers = {CharacterRepositoryTest.Initializer.class})
public class CharacterRepositoryTest {

    @Container
    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>("postgres:alpine")
            .withDatabaseName("test")
            .withUsername("postgresql")
            .withPassword("password");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    static {
        postgreSQLContainer.start();
    }

    @Autowired
    private CharacterRepository characterRepository;

    @Test
    @DisplayName("Test which should save character. This test is done for learning purposes")
    public void shouldSaveCharacter() {
        Character character = new Character(1L, "Isaac", new BigDecimal("3.50"), 6,
                new BigDecimal("1.0"), new BigDecimal(0));
        Character actualCharacter = characterRepository.save(character);
        assertThat(actualCharacter).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(character);
    }
}