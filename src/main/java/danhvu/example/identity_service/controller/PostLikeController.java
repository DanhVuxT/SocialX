package danhvu.example.identity_service.controller;

import danhvu.example.identity_service.dto.response.ApiResponse;
import danhvu.example.identity_service.enums.ApiResponseCode;
import danhvu.example.identity_service.service.LikeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostLikeController {
    LikeService likeService;

    @PostMapping("/post/{postId}")
    public ApiResponse<String> toggleLike(@PathVariable Long postId, @AuthenticationPrincipal Jwt jwt) {
        return ApiResponse.from(ApiResponseCode.SUCCESS, likeService.toggleLike(postId, jwt.getSubject()));
    }
}