package danhvu.example.identity_service.repository;

import danhvu.example.identity_service.entity.PostLike;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    long countByPostId(Long postId);

    boolean existsByUserIdAndPostId(String userId, Long postId);

    @Modifying
    @Transactional
    @Query("DELETE FROM PostLike l WHERE l.user.id = :userId AND l.post.id = :postId")
    void deleteByUserIdAndPostId(@Param("userId") String userId, @Param("postId") Long postId);
}
