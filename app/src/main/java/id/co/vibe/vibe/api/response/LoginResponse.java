package id.co.vibe.vibe.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.co.vibe.vibe.base.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse extends BaseResponse {

    LoginResponse.Data data;

    @Getter
    @NoArgsConstructor
    public static class Data {
        @SerializedName("_id")
        @Expose
        String id;
        String email;
        String address;
        String description;
        String displayName;
        String profilePicture;
        String role;
        SocialAccount socialAccount;
        String username;
        Boolean emailVerified;
        String accessToken;
        String refreshToken;
        Boolean isPrivate;
    }

    @Getter
    @NoArgsConstructor
    static class SocialAccount {
        LoginResponse.Google google;
        LoginResponse.Facebook facebook;
    }

    @Getter
    @NoArgsConstructor
    static class Facebook {
        String profileId;
    }

    @Getter
    @NoArgsConstructor
    static class Google {
        String profileId;
    }

}
