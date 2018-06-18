package id.co.vibe.vibe.base;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BaseResponse {
    Integer code;
    String message;
    String type;
}
