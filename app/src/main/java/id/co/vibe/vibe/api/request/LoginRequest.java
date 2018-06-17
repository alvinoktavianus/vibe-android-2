package id.co.vibe.vibe.api.request;

public class LoginRequest {

    private String email;
    private String password;
    private String clientId;
    private String deviceToken;

    public LoginRequest(String email, String password, String clientId, String deviceToken) {
        this.email = email;
        this.password = password;
        this.clientId = clientId;
        this.deviceToken = deviceToken;
    }

}
