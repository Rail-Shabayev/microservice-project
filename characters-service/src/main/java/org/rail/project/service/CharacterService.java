package org.rail.project.service;

import lombok.RequiredArgsConstructor;
import org.rail.project.dto.CharacterDto;
import org.rail.project.model.Character;
import org.rail.project.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

    @Transactional(readOnly = true)
    public List<CharacterDto> getAllCharacters() {
        return characterRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    public CharacterDto mapToDto(Character character) {
        return CharacterDto.builder()
                .name(character.getName())
                .health(character.getHealth())
                .damage(character.getDamage())
                .speed(character.getSpeed())
                .luck(character.getLuck())
                .build();
    }

    @Transactional(readOnly = true)
    public CharacterDto getRandomCharacter() {
        Random rand = new Random();
        List<CharacterDto> characterDtos = characterRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
        int randomNumber = rand.nextInt(characterDtos.size());
        return characterDtos.get(randomNumber);
    }

    @Transactional(readOnly = true)
    public CharacterDto getCharacter(Long id) throws Exception {
        return characterRepository.findAll().stream()
                .filter(character -> character.getId().equals(id))
                .findFirst()
                .map(this::mapToDto)
                .orElseThrow(() -> new Exception("No character with that name!"));
    }

}
