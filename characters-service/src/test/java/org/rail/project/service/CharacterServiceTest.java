package org.rail.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rail.project.dto.CharacterDto;
import org.rail.project.model.Character;
import org.rail.project.repository.CharacterRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CharacterServiceTest {

    CharacterService characterService;
    Character character;
    CharacterDto characterDto;
    Character character1;
    CharacterDto characterDto1;

    @BeforeEach
    void setUp() {
        characterService = new CharacterService(characterRepository);
        character = new Character(1L, "Isaac", new BigDecimal("3.50"), 6,
                new BigDecimal("1.0"), new BigDecimal(0));
        characterDto = new CharacterDto("Isaac", new BigDecimal("3.50"), 6,
                new BigDecimal("1.0"), new BigDecimal(0));
        character1 = new Character(2L, "Magdalene", new BigDecimal("3.50"), 8,
                new BigDecimal("0.85"), new BigDecimal(0));
        characterDto1 = new CharacterDto("Magdalene", new BigDecimal("3.50"), 8,
                new BigDecimal("0.85"), new BigDecimal(0));
    }

    @Mock
    private CharacterRepository characterRepository;

    @Test
    @DisplayName("Should return all characters")
    void shouldReturnAllCharacters() {
        List<Character> characterList = List.of(character, character1);
        List<CharacterDto> expectedCharacterList = List.of(characterDto, characterDto1);
        when(characterRepository.findAll()).thenReturn(characterList);
        List<CharacterDto> listOfCharacterDtos = characterService.getAllCharacters();
        assertThat(listOfCharacterDtos.get(1).getName()).isEqualTo(expectedCharacterList.get(1).getName());
        assertThat(listOfCharacterDtos.get(1).getHealth()).isEqualTo(expectedCharacterList.get(1).getHealth());
    }

    @Test
    @DisplayName("Should map given character to characterDto")
    void shouldMapCharacterToDto() {
        CharacterDto actualCharacterDto = characterService.mapToDto(character);
        assertThat(characterService.mapToDto(character).getName()).isEqualTo(actualCharacterDto.getName());
        assertThat(characterService.mapToDto(character).getHealth()).isEqualTo(actualCharacterDto.getHealth());
    }

    @Test
    @DisplayName("Should return a random character")
    void shouldReturnRandomCharacter() {
        List<Character> characterList = List.of(character, character1);
        List<CharacterDto> characterDtos = List.of(characterDto, characterDto1);
        when(characterRepository.findAll()).thenReturn(characterList);
        CharacterDto actualCharacter = characterService.getRandomCharacter();
        assertThat(actualCharacter).isIn(characterDtos);
    }

    @Test
    @DisplayName("Should return a character")
    void shouldReturnCharacter() throws Exception {
        when(characterRepository.findAll()).thenReturn(List.of(character, character1));
        CharacterDto actualCharacter = characterService.getCharacter(1L);
        assertThat(characterService.getCharacter(1L).getName()).isEqualTo(actualCharacter.getName());
        assertThat(characterService.getCharacter(1L).getHealth()).isEqualTo(actualCharacter.getHealth());
    }
}