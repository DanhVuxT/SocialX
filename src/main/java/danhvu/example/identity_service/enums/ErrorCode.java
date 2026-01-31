package danhvu.example.identity_service.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    // Auth & User
    INVALID_TOKEN(1009, "Token không hợp lệ", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED(1010, "Phiên làm việc đã hết hạn", HttpStatus.UNAUTHORIZED),
    USER_EXISTED(1002, "Tên người dùng đã tồn tại", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Tên người dùng phải có ít nhất {min} ký tự", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Mật khẩu phải có ít nhất {min} ký tự", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "Người dùng không tồn tại", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Chưa xác thực danh tính", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "Bạn không có quyền truy cập", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Độ tuổi của bạn phải ít nhất là {min}", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1011, "Email này đã được sử dụng", HttpStatus.BAD_REQUEST),

    // Social Features (Posts, Comments, Likes)
    POST_NOT_FOUND(2001, "Không tìm thấy bài viết", HttpStatus.NOT_FOUND),
    COMMENT_NOT_FOUND(2002, "Không tìm thấy bình luận", HttpStatus.NOT_FOUND),
    NOT_POST_OWNER(2003, "Bạn không có quyền chỉnh sửa bài viết của người khác", HttpStatus.FORBIDDEN),

    // Relationship (Friends, Follows)
    ALREADY_FRIENDS(3001, "Hai bạn đã là bạn bè", HttpStatus.BAD_REQUEST),
    FRIEND_REQUEST_NOT_FOUND(3002, "Không tìm thấy lời mời kết bạn", HttpStatus.NOT_FOUND),

    // File & Resource
    FILE_TOO_LARGE(4001, "Kích thước tệp quá lớn", HttpStatus.PAYLOAD_TOO_LARGE),
    INVALID_FILE_FORMAT(4002, "Định dạng tệp không hợp lệ (Chỉ chấp nhận JPG, PNG)", HttpStatus.BAD_REQUEST),

    // Validation & Input
    INVALID_INPUT(5001, "Dữ liệu đầu vào không hợp lệ", HttpStatus.BAD_REQUEST),
    ;

    int code;
    String message;
    HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
