package goorm.zzaturi.domain.like.controller;

import goorm.zzaturi.domain.like.dto.LikeRequestDto;
import goorm.zzaturi.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Void> like(@RequestBody LikeRequestDto requestDto) {
        likeService.like(requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> unlike(@RequestBody LikeRequestDto requestDto) {
        likeService.unlike(requestDto);
        return ResponseEntity.ok().build();
    }
}
