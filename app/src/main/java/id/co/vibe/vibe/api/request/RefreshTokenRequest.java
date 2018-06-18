package id.co.vibe.vibe.api.request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RefreshTokenRequest {
    String token;
    String clientId;
}
