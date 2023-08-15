package org.rail.project.controller;

import lombok.RequiredArgsConstructor;
import org.rail.project.dto.CharacterDto;
import org.rail.project.service.CharacterService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/maker")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void postCharacter(@RequestBody CharacterDto characterDto) {
        characterService.saveCharacter(characterDto);
    }
}
