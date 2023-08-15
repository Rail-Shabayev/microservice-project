package org.rail.project.service;

import lombok.RequiredArgsConstructor;
import org.rail.project.dto.CharacterDto;
import org.rail.project.model.Character;
import org.rail.project.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

    public void saveCharacter(CharacterDto characterDto) {
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
