package danhvu.example.identity_service.service;

import danhvu.example.identity_service.entity.*;
import danhvu.example.identity_service.enums.ErrorCode;
import danhvu.example.identity_service.exception.AppException;
import danhvu.example.identity_service.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LikeService {
    PostLikeRepository likeRepository;
    PostRepository postRepository;
    UserRepository userRepository;
    CommentLikeRepository commentLikeRepository;
    CommentRepository commentRepository;

    public String toggleLike(Long postId, String userId) {
        if (likeRepository.existsByUserIdAndPostId(userId, postId)) {
            likeRepository.deleteByUserIdAndPostId(userId, postId);
            return "Unliked";
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        PostLike like = PostLike.builder()
                .user(user)
                .post(post)
                .build();
        likeRepository.save(like);
        return "Liked";
    }

    public String toggleLikeComment(Long commentId, String userId) {
        if (commentLikeRepository.existsByUserIdAndCommentId(userId, commentId)) {
            commentLikeRepository.deleteByUserIdAndCommentId(userId, commentId);
            return "Unliked Comment";
        }

        User user = userRepository.findById(userId).get();
        Comment comment = commentRepository.findById(commentId).get();

        commentLikeRepository.save(CommentLike.builder()
                .user(user)
                .comment(comment)
                .build());
        return "Liked Comment";
    }
}
