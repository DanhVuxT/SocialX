package danhvu.example.identity_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String text;
    LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    Post post;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    Comment parent;

    @OneToMany(mappedBy = "parent")
    List<Comment> replies;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
