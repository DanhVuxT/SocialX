package danhvu.example.identity_service.service;

import danhvu.example.identity_service.dto.request.CommentRequest;
import danhvu.example.identity_service.dto.response.CommentResponse;
import danhvu.example.identity_service.entity.Comment;
import danhvu.example.identity_service.entity.Post;
import danhvu.example.identity_service.entity.User;
import danhvu.example.identity_service.enums.ErrorCode;
import danhvu.example.identity_service.exception.AppException;
import danhvu.example.identity_service.mapper.CommentMapper;
import danhvu.example.identity_service.repository.CommentRepository;
import danhvu.example.identity_service.repository.PostRepository;
import danhvu.example.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;
    UserRepository userRepository;
    CommentMapper commentMapper;

    public CommentResponse addComment(Long postId, CommentRequest request, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        Comment comment = commentMapper.toComment(request, user, post);

        return commentMapper.toCommentResponse(commentRepository.save(comment));
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByPost(Long postId) {
        return commentRepository.findAllByPostIdOrderByCreatedAtDesc(postId).stream()
                .map(commentMapper::toCommentResponse)
                .toList();
    }

    public void deleteComment(Long commentId, String userId, String userRole) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        boolean isCommentOwner = comment.getUser().getId().equals(userId);
        boolean isPostOwner = comment.getPost().getUser().getId().equals(userId);
        boolean isAdmin = userRole.equals("ADMIN");

        if (!isCommentOwner && !isPostOwner && !isAdmin) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        commentRepository.delete(comment);
    }
}

