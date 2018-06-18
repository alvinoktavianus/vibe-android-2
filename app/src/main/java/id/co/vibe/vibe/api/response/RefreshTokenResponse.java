package id.co.vibe.vibe.api.response;

import id.co.vibe.vibe.base.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefreshTokenResponse extends BaseResponse {

    RefreshTokenResponse.Data data;

    @Getter
    @NoArgsConstructor
    public static class Data {
        Boolean success;
        String accessToken;
        String refreshToken;
    }

}
