package id.co.vibe.vibe.ui.activity.login;

import id.co.vibe.vibe.api.request.LoginRequest;
import id.co.vibe.vibe.api.response.LoginResponse;
import io.reactivex.Observable;

public interface LoginInteractor {

    Observable<LoginResponse> doEmailPasswordLogin(LoginRequest loginRequest);

}
