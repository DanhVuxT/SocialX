package danhvu.example.identity_service.repository;

import danhvu.example.identity_service.entity.Comment;
import danhvu.example.identity_service.entity.CommentLike;
import danhvu.example.identity_service.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    boolean existsByUserIdAndCommentId(String userId, Long commentId);

    void deleteByUserIdAndCommentId(String userId, Long commentId);

    long countByCommentId(Long commentId);
}
