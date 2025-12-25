package danhvu.example.identity_service.enums;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ApiResponseCode {
    SUCCESS_CREATE(1000, "tao moi thanh cong"),
    SUCCESS_UPDATE(1001, "cap naht thanh cong"),
    SUCCESS_DELETE(1002, "xoa thanh cong"),
    SUCCESS_GET(1003, "xem thanh cong"),
    SUCCESS_GET_ALL(1004, "lay danh sach thanh cong")
    ;

    int code;
    String message;

    ApiResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
