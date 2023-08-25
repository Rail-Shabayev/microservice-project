package org.rail.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rail.project.dto.CharacterDto;
import org.rail.project.model.Character;
import org.rail.project.repository.CharacterRepository;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CharacterServiceTest {

    @Mock
    private CharacterRepository characterRepository;

    @Mock
    private WebClient.Builder webClientBuilder;

    @Captor
    private ArgumentCaptor<Character> characterArgumentCaptor;


    CharacterService characterService;

    @BeforeEach
    void setUp() {
        characterService = new CharacterService(characterRepository, webClientBuilder);
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
    @DisplayName("Should map a CharacterDto object to a Character")
    void shouldMapToModel() {
        Character actualCharacter = new Character(1L, "Isaac", new BigDecimal("3.50"), 6,
                new BigDecimal("1.0"), new BigDecimal(0));
        CharacterDto characterDto = new CharacterDto("Isaac", new BigDecimal("3.50"), 6,
                new BigDecimal("1.0"), new BigDecimal(0));
        assertThat(CharacterService.mapToModel(characterDto).getName()).isEqualTo(actualCharacter.getName());
        assertThat(CharacterService.mapToModel(characterDto).getDamage()).isEqualTo(actualCharacter.getDamage());
    }
//    Couldn't make test for that case :(
//    @DisplayName("When receiving a character's name with the" +
//            " same character's name as in the database then test should fail ")
//    void shouldFailWithSameCharacterName() throws Exception {
//        CharacterDto characterDto = new CharacterDto("Judas", new BigDecimal("4.72"), 2,
//                new BigDecimal("1.0"), new BigDecimal(0));
//        String clientBody = "{\"name\":\"Judas\", \"damage\":4.72, \"health\":2,\"speed\":1.00,\"luck\":0.00}";
//        ClientResponse clientResponse = ClientResponse
//                .create(HttpStatus.CREATED)
//                .header("Content-Type", "application/json")
//                .body(clientBody)
//                .build();
//        ExchangeFunction function = new ExchangeFunction() {
//            @Override
//            public Mono<ClientResponse> exchange(ClientRequest request) {
//                return Mono.just(clientResponse);
//            }
//        };
//
//        when(objectMapper.readValue(clientBody, new TypeReference<CharacterDto>(){})).thenReturn(characterDto);
//        webClientBuilder = WebClient.builder().exchangeFunction(function);
//        characterService = new CharacterService(characterRepository, webClientBuilder);
//
//        assertThatRuntimeException().isThrownBy(() -> characterService.saveCharacter(characterDto))
//                .withMessage("A character with that name is already exists");
//    }
}