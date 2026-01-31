package danhvu.example.identity_service.exception;

import danhvu.example.identity_service.dto.response.ApiResponse;
import danhvu.example.identity_service.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.security.core.AuthenticationException;

@RestControllerAdvice
public class GlobalHandlingException {

    // 1. Hứng các lỗi "không xác định" (Lỗi logic code, NullPointer, v.v.)
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(Exception exception) {
        // Thay 'UNCATEGORIZED_EXCEPTION' bằng cái bạn có, ví dụ ErrorCode.INVALID_KEY
        // hoặc bất cứ cái nào bạn dùng làm mặc định
        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;

        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(exception.getMessage()) // Để tạm đây để debug cho dễ
                        .build());
    }

    // 2. Hứng lỗi do bạn tự quăng ra (AppException)
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(ApiResponse.from(errorCode));
    }

    // 3. Hứng lỗi bảo mật (Token sai, Token hết hạn, Token đã logout)
    @ExceptionHandler(value = AuthenticationException.class)
    ResponseEntity<ApiResponse> handlingAuthenticationException(AuthenticationException exception){
        // In ra console để bạn chắc chắn là nó đã chạy vào đây
        System.out.println("Đã bắt được lỗi Security: " + exception.getMessage());

        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;
        String message = errorCode.getMessage();

        // Lấy message từ "Caused by" (là cái JwtException bạn quăng ra)
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
