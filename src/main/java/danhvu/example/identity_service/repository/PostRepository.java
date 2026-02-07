package danhvu.example.identity_service.repository;

import danhvu.example.identity_service.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc(String userId);

    List<Post> findAllByUser_UsernameOrderByCreatedAtDesc(String username);
    @Query("SELECT p FROM Post p JOIN FETCH p.user")
    List<Post> findAllWithUser();
}
