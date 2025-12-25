package danhvu.example.identity_service.controller;

import danhvu.example.identity_service.dto.request.UserCreateRequest;
import danhvu.example.identity_service.dto.request.UserUpdateRequest;
import danhvu.example.identity_service.dto.response.ApiResponse;
import danhvu.example.identity_service.dto.response.UserResponse;
import danhvu.example.identity_service.enums.ApiResponseCode;
import danhvu.example.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request) {
        return ApiResponse.from(ApiResponseCode.SUCCESS_CREATE, userService.createUser(request));
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.from(ApiResponseCode.SUCCESS_GET_ALL, userService.getUsers());
    }
    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        return ApiResponse.from(ApiResponseCode.SUCCESS_GET, userService.getUser(userId));
    }

    @GetMapping("/my-info")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.from(ApiResponseCode.SUCCESS_GET, userService.getMyInfo());
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ApiResponse.from(ApiResponseCode.SUCCESS_DELETE);
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return ApiResponse.from(ApiResponseCode.SUCCESS_UPDATE, userService.updateUser(userId, request));
    }
}
