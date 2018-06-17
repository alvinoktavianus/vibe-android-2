package id.co.vibe.vibe.ui.activity.login;

import id.co.vibe.vibe.api.request.LoginRequest;
import id.co.vibe.vibe.base.BasePresenter;

public interface LoginPresenter extends BasePresenter<LoginView> {

    void handleCheckInButton(LoginRequest loginRequest);

}
