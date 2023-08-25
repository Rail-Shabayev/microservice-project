package org.rail.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.rail.project.dto.CharacterDto;
import org.rail.project.model.Character;
import org.rail.project.repository.CharacterRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final WebClient.Builder webClientBuilder;
    ObjectMapper objectMapper = new ObjectMapper();

    public void saveCharacter(CharacterDto characterDto) throws JsonProcessingException {
        String existingCharacters = Objects.requireNonNull(webClientBuilder.build()
                .get()
                .uri("http://characters-service/api/characters")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block());
        List<CharacterDto> characterDtos = objectMapper.readValue(existingCharacters, new TypeReference<>(){});

        if (characterDtos.stream()
                .map(CharacterDto::getName)
                .anyMatch(s -> s.equals(characterDto.getName())))
            throw new RuntimeException("A character with that name is already exists");
        else
            characterRepository.save(mapToModel(characterDto));
    }

    public static Character mapToModel(CharacterDto characterDto) {
        return Character.builder()
                .name(characterDto.getName())
                .speed(characterDto.getSpeed())
                .damage(characterDto.getDamage())
                .health(characterDto.getHealth())
                .luck(characterDto.getLuck())
                .build();
    }

}
