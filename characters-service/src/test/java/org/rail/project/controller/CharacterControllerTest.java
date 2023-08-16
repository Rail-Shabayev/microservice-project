package org.rail.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rail.project.dto.CharacterDto;
import org.rail.project.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CharacterController.class)
public class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService characterService;

    @Test
    @DisplayName("Should list  all characters " +
            "when making GET request to an endpoint - /api/characters")
    public void shouldReturnCharacters() throws Exception {
        CharacterDto characterDto1 = new CharacterDto("Magdalene", new BigDecimal("3.50"), 8,
                new BigDecimal("0.85"), new BigDecimal(0));
        CharacterDto characterDto = new CharacterDto("Isaac", new BigDecimal("3.50"), 6,
                new BigDecimal("1.0"), new BigDecimal(0));

        when(characterService.getAllCharacters()).thenReturn(Arrays.asList(characterDto, characterDto1));
        mockMvc.perform(get("/api/characters"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Isaac")))
                .andExpect(jsonPath("$[0].health", is(6)))
                .andExpect(jsonPath("$[1].name", is("Magdalene")))
                .andExpect(jsonPath("$[1].health", is(8)));
    }


    @Test
    @DisplayName("Should get a random Character object")
    void shouldGetRandomCharacter() throws Exception {
        CharacterDto characterDto = new CharacterDto("Isaac", new BigDecimal("3.50"), 6,
                new BigDecimal("1.0"), new BigDecimal(0));
        when(characterService.getRandomCharacter()).thenReturn(characterDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/characters/random")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name", is("Isaac")))
                .andExpect(jsonPath("$.health", is(6)))
                .andExpect(jsonPath("$.speed", is(1.0)));
    }

    @Test
    void getCharacter() {
    }
}