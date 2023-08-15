package org.rail.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rail.project.model.Character;
import org.rail.project.repository.CharacterRepository;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CharacterServiceTest {

    @Mock
    private CharacterRepository characterRepository;

    @Captor
    private ArgumentCaptor<Character> characterArgumentCaptor;


    CharacterService characterService;

    @BeforeEach
    void setUp() {
        characterService = new CharacterService(characterRepository);
    }

    @Test
    @DisplayName("should save character")
    void shouldSaveCharacter() {
        Character character = new Character(1L, "Isaac", new BigDecimal("3.50"), 6,
                new BigDecimal("1.0"), new BigDecimal(0));
        characterRepository.save(character);
        verify(characterRepository, times(1)).save(characterArgumentCaptor.capture());

        assertThat(characterArgumentCaptor.getValue().getId()).isEqualTo(1L);
        assertThat(characterArgumentCaptor.getValue().getName()).isEqualTo("Isaac");
    }

    @Test
    void mapToModel() {

    }
}