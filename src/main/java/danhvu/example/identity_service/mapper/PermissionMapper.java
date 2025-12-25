package danhvu.example.identity_service.mapper;

import danhvu.example.identity_service.dto.request.PermissionRequest;
import danhvu.example.identity_service.dto.response.PermissionResponse;
import danhvu.example.identity_service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
