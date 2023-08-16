package org.rail.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.rail.project.dto.CharacterDto;
import org.rail.project.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CharacterController.class)
class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CharacterService characterService;

    @Test
    @DisplayName("Should save a Character")
    void shouldSaveCharacter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/maker")
                        .contentType(APPLICATION_JSON)
                        .content("{\"name\":\"Judas\", \"damage\":4.72, \"health\":2,\"speed\":1.00,\"luck\":0.00}")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(characterService).saveCharacter(ArgumentMatchers.any(CharacterDto.class));
    }
}