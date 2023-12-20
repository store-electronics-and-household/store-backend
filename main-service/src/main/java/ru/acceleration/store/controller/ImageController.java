package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.image.ImageDto;
import ru.acceleration.store.dto.image.NewImageDto;
import ru.acceleration.store.service.image.ImageService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/images")
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000", "https://cyberplace.online", "http://cyberplace.online", "http://45.12.236.120"})
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ImageDto> uploadImage(@Valid @RequestBody NewImageDto newImageDto) {
        log.info("POST /images/upload");
        return ResponseEntity.status(201).body(imageService.uploadImage(newImageDto));
    }
}
