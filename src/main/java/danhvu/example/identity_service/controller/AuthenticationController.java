package danhvu.example.identity_service.controller;

import java.text.ParseException;

import danhvu.example.identity_service.dto.response.ApiResponse;
import danhvu.example.identity_service.enums.ApiResponseCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import danhvu.example.identity_service.dto.request.*;
import danhvu.example.identity_service.dto.response.AuthenticationResponse;
import danhvu.example.identity_service.dto.response.IntrospectResponse;
import danhvu.example.identity_service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ApiResponse.from(ApiResponseCode.SUCCESS_UPDATE, authenticationService.authenticate(request));
    }

//    @PostMapping("/introspect")
//    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
//            throws ParseException, JOSEException {
//        var result = authenticationService.introspect(request);
//        return ApiResponse.<IntrospectResponse>builder().result(result).build();
//    }
//
//    @PostMapping("/refresh")
//    ApiResponse<AuthenticationResponse> authenticate(@RequestBody RefreshRequest request)
//            throws ParseException, JOSEException {
//        var result = authenticationService.refreshToken(request);
//        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
//    }
//
//    @PostMapping("/logout")
//    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
//        authenticationService.logout(request);
//        return ApiResponse.<Void>builder().build();
//    }
}