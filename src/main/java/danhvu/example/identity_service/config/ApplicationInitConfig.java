package danhvu.example.identity_service.config;

import java.util.HashSet;
import java.util.Set;

import danhvu.example.identity_service.constant.PredefinedPermission;
import danhvu.example.identity_service.entity.Permission;
import danhvu.example.identity_service.repository.PermissionRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import danhvu.example.identity_service.constant.PredefinedRole;
import danhvu.example.identity_service.entity.Role;
import danhvu.example.identity_service.entity.User;
import danhvu.example.identity_service.repository.RoleRepository;
import danhvu.example.identity_service.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        return args -> {
            Permission roleRead = permissionRepository.findById(PredefinedPermission.ROLE_READ)
                    .orElseGet(() -> permissionRepository.save(
                            Permission.builder()
                                    .name(PredefinedPermission.ROLE_READ)
                                    .build()
                    ));

            Role userRole = roleRepository.findById(PredefinedRole.USER_ROLE)
                    .orElseGet(() -> roleRepository.save(
                            Role.builder()
                                    .name(PredefinedRole.USER_ROLE)
                                    .permissions(Set.of(roleRead))
                                    .build()
                    ));

            Role adminRole = roleRepository.findById(PredefinedRole.ADMIN_ROLE)
                    .orElseGet(() -> roleRepository.save(
                            Role.builder()
                                    .name(PredefinedRole.ADMIN_ROLE)
                                    .permissions(new HashSet<>(permissionRepository.findAll()))
                                    .build()
                    ));

            if (userRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {
                userRepository.save(User.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .roles(Set.of(adminRole))
                        .build());
            }
        };
    }
}