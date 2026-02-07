package danhvu.example.identity_service.controller;

import danhvu.example.identity_service.dto.request.PostRequest;
import danhvu.example.identity_service.dto.response.ApiResponse;
import danhvu.example.identity_service.dto.response.PostResponse;
import danhvu.example.identity_service.enums.ApiResponseCode;
import danhvu.example.identity_service.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;

    @PostMapping
    public ApiResponse<PostResponse> createPost(
            @RequestBody PostRequest request,
            @AuthenticationPrincipal Jwt jwt) {

        String userId = jwt.getSubject();

        return ApiResponse.from(ApiResponseCode.POST_CREATED, postService.createPost(request, userId));
    }

    @GetMapping("/{postId}")
    public ApiResponse<PostResponse> getPost(
            @PathVariable Long postId,
            @AuthenticationPrincipal Jwt jwt) {

        String userId = jwt.getSubject();

        return ApiResponse.from(ApiResponseCode.SUCCESS_GET_ALL, postService.getPostById(postId, userId));
    }

    @GetMapping("/user/{username}")
    public ApiResponse<List<PostResponse>> getPostsByUser(
            @PathVariable String username,
            @AuthenticationPrincipal Jwt jwt) {

        String currentUserId = jwt.getSubject();

        return ApiResponse.from(ApiResponseCode.SUCCESS_GET_ALL,
                postService.getAllPostsByUserName(username, currentUserId));
    }

    @PutMapping("/{postId}")
    public ApiResponse<PostResponse> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequest request,
            @AuthenticationPrincipal Jwt jwt) {

        String userId = jwt.getSubject();
        return ApiResponse.from(ApiResponseCode.SUCCESS_UPDATE, postService.updatePost(postId, request, userId));
    }

    @DeleteMapping("/{postId}")
    public ApiResponse<String> deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal Jwt jwt) {

        String userId = jwt.getSubject();

        String scope = jwt.getClaim("scope");

        postService.deletePost(postId, userId, scope);
        return ApiResponse.from(ApiResponseCode.SUCCESS_DELETE);
    }
}