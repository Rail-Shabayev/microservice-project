package org.rail.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    @CircuitBreaker(name="characters", fallbackMethod = "fallbackMethod")
    public void postCharacter(@RequestBody CharacterDto characterDto) throws JsonProcessingException {
        characterService.saveCharacter(characterDto);
    }
    @SuppressWarnings("unused")
    public void  fallbackMethod(CharacterDto CharacterDto, RuntimeException RuntimeException) {
        System.out.println("Characters service is not responding. Please try again or wait for 1 hour!");
    }
}
