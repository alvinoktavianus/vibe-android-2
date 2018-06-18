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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getClientId() {
        return clientId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

}
