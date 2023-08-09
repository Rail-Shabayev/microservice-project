package org.rail.project.controller;

import lombok.RequiredArgsConstructor;
import org.rail.project.dto.CharacterDto;
import org.rail.project.service.CharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping
    @ResponseStatus(OK)
    public List<CharacterDto> getCharacters() {
        return characterService.getAllCharacters();
    }

    @GetMapping(value = "/random")
    @ResponseStatus(OK)
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping(value = "/character/{id}")
    @ResponseStatus(OK)
    public CharacterDto getCharacter(@PathVariable("id") Long id) throws Exception {
        return characterService.getCharacter(id);
    }
}






