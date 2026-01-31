package danhvu.example.identity_service.mapper;

import danhvu.example.identity_service.dto.request.UserCreateRequest;
import danhvu.example.identity_service.dto.request.UserUpdateRequest;
import danhvu.example.identity_service.dto.response.UserResponse;
import danhvu.example.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
