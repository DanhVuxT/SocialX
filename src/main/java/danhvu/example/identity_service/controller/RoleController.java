package danhvu.example.identity_service.controller;

import java.util.List;

import danhvu.example.identity_service.enums.ApiResponseCode;
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
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.from(ApiResponseCode.SUCCESS_CREATE, roleService.create(request));
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.from(ApiResponseCode.SUCCESS_GET_ALL, roleService.getAll());
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role) {
        roleService.delete(role);
        return ApiResponse.from(ApiResponseCode.SUCCESS_DELETE);
    }
}