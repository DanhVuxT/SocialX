package danhvu.example.identity_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponse {
    String text;
    LocalDateTime createdAt;
    String username;
    long likeCount;
    boolean isLikedByMe;
    List<CommentResponse> replies;

}
