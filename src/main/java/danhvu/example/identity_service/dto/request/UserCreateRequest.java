package danhvu.example.identity_service.dto.request;

import danhvu.example.identity_service.entity.Role;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
        String username;
        String email;
        String phoneNumber;
        String password;
        Date dob;
        Set<String> roles;
}
