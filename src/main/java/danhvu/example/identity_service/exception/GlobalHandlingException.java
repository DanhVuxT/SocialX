package danhvu.example.identity_service.exception;

import danhvu.example.identity_service.dto.response.ApiResponse;
import danhvu.example.identity_service.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.security.core.AuthenticationException;

@RestControllerAdvice
public class GlobalHandlingException {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(Exception exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;

        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(ApiResponse.from(errorCode));
    }

    @ExceptionHandler(value = AuthenticationException.class)
    ResponseEntity<ApiResponse> handlingAuthenticationException(AuthenticationException exception){
        System.out.println("Đã bắt được lỗi Security: " + exception.getMessage());

        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;
        String message = errorCode.getMessage();

        if (exception.getCause() != null && exception.getCause().getMessage() != null) {
            message = exception.getCause().getMessage();
        } else if (exception.getMessage().contains("Token này đã đăng xuất")) {
            message = "Token này đã đăng xuất";
        }

        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(message)
                        .build());
    }
}
