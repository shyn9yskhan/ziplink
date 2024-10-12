package kz.ziplink.id_generation_service.controller;

import kz.ziplink.id_generation_service.service.IdGenerationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/idGeneration")
public class IdGenerationController {

    IdGenerationService idGenerationService;

    public IdGenerationController(IdGenerationService idGenerationService) {
        this.idGenerationService = idGenerationService;
    }

    @GetMapping("/userId")
    public ResponseEntity<String> generateUserId() {
        return new ResponseEntity<>(idGenerationService.generateUserId(), HttpStatus.OK);
    }

    @GetMapping("/profileId")
    public ResponseEntity<String> generateProfileId() {
        return new ResponseEntity<>(idGenerationService.generateProfileId(), HttpStatus.OK);
    }
}
