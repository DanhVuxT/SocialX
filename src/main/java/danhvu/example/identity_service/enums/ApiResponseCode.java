package danhvu.example.identity_service.enums;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ApiResponseCode {
    // 10xx: General Success
    SUCCESS(1000, "Thao tác thành công"),
    SUCCESS_CREATE(1001, "Tạo mới thành công"),
    SUCCESS_UPDATE(1002, "Cập nhật thành công"),
    SUCCESS_DELETE(1003, "Xóa thành công"),
    SUCCESS_GET(1004, "Lấy dữ liệu thành công"),
    SUCCESS_GET_ALL(1005, "Lấy danh sách thành công"),

    // 11xx: Auth & User
    LOGIN_SUCCESS(1101, "Đăng nhập thành công"),
    LOGOUT_SUCCESS(1102, "Đăng xuất thành công"),
    REGISTER_SUCCESS(1103, "Đăng ký tài khoản thành công"),
    PASSWORD_CHANGED(1104, "Thay đổi mật khẩu thành công"),

    // 12xx: Social Features (Posts, Interactions)
    POST_CREATED(1201, "Đã đăng bài viết thành công"),
    COMMENT_ADDED(1202, "Đã thêm bình luận"),
    LIKED_SUCCESS(1203, "Đã bày tỏ cảm xúc"),
    FRIEND_REQUEST_SENT(1204, "Đã gửi lời mời kết bạn"),
    FRIEND_REQUEST_ACCEPTED(1205, "Đã đồng ý kết bạn"),

    // 13xx: System & Files
    UPLOAD_SUCCESS(1301, "Tải tệp lên thành công"),
    PROCESSING(1302, "Yêu cầu đang được xử lý"), // Dùng cho các tác vụ tốn thời gian
    ;

    int code;
    String message;

    ApiResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
