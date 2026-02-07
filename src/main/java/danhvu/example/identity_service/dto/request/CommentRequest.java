package danhvu.example.identity_service.dto.request;

import danhvu.example.identity_service.dto.response.CommentResponse;
import danhvu.example.identity_service.dto.response.UserResponse;
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
public class CommentRequest {
    String text;
    String userId;
}
