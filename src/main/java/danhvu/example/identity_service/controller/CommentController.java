package danhvu.example.identity_service.controller;

import danhvu.example.identity_service.dto.request.CommentRequest;
import danhvu.example.identity_service.dto.response.ApiResponse;
import danhvu.example.identity_service.dto.response.CommentResponse;
import danhvu.example.identity_service.enums.ApiResponseCode;
import danhvu.example.identity_service.service.CommentService;
import danhvu.example.identity_service.service.LikeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    CommentService commentService;
    LikeService likeService;

    @PostMapping("/post/{postId}")
    public ApiResponse<CommentResponse> addComment(
            @PathVariable Long postId,
            @RequestBody CommentRequest request,
            @AuthenticationPrincipal Jwt jwt) {
        return ApiResponse.from(ApiResponseCode.SUCCESS, commentService.addComment(postId, request, jwt.getSubject()));
    }

    @GetMapping("/post/{postId}")
    public ApiResponse<List<CommentResponse>> getComments(@PathVariable Long postId) {
        return ApiResponse.from(ApiResponseCode.SUCCESS, commentService.getCommentsByPost(postId));
    }

    @DeleteMapping("/{commentId}")
    public ApiResponse<Void> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal Jwt jwt) {

        String userId = jwt.getSubject();
        String role = jwt.getClaimAsString("scope");

        commentService.deleteComment(commentId, userId, role);
        return ApiResponse.from(ApiResponseCode.SUCCESS_DELETE);
    }

    @PostMapping("/like/{commentId}")
    public ApiResponse<String> toggleLike(
            @PathVariable Long commentId,
            @AuthenticationPrincipal Jwt jwt) {

        String userId = jwt.getSubject();
        return ApiResponse.from(ApiResponseCode.SUCCESS, likeService.toggleLikeComment(commentId, userId));
    }
}