package danhvu.example.identity_service.controller;

import java.util.List;

import danhvu.example.identity_service.dto.request.PermissionRequest;
import danhvu.example.identity_service.dto.response.PermissionResponse;
import danhvu.example.identity_service.enums.ApiResponseCode;
import danhvu.example.identity_service.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import danhvu.example.identity_service.dto.response.ApiResponse;
import danhvu.example.identity_service.dto.request.RoleRequest;
import danhvu.example.identity_service.dto.response.RoleResponse;
import danhvu.example.identity_service.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return ApiResponse.from(ApiResponseCode.SUCCESS_CREATE, permissionService.create(request));
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.from(ApiResponseCode.SUCCESS_GET_ALL, permissionService.getAll());
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission) {
        permissionService.delete(permission);
        return ApiResponse.from(ApiResponseCode.SUCCESS_DELETE);
    }
}