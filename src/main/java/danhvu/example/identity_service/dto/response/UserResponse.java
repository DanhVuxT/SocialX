package danhvu.example.identity_service.dto.response;

import danhvu.example.identity_service.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String username;
    String email;
    String phoneNumber;
    Date dob;
    Set<RoleResponse> roles;
}
