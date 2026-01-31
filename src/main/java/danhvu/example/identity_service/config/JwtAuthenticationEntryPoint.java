package danhvu.example.identity_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import danhvu.example.identity_service.dto.response.ApiResponse;
import danhvu.example.identity_service.enums.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        // 1. Định nghĩa ErrorCode bạn muốn trả về
        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;

        // 2. Thiết lập Header cho Response
        response.setStatus(errorCode.getStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        // 3. Tạo Body JSON (Dùng đúng cấu trúc ApiResponse của bạn)
        // Lưu ý: Thay đổi message nếu là lỗi "đã đăng xuất"
        String message = errorCode.getMessage();
        if (authException.getMessage().contains("Token này đã đăng xuất")) {
            message = "Token này đã đăng xuất";
        }

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(message)
                .build();

        // 4. Dùng ObjectMapper để biến Object thành chuỗi JSON và gửi đi
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));

        // 5. Kết thúc response tại đây, không cho nó văng đi đâu nữa
        response.flushBuffer();
    }
}
