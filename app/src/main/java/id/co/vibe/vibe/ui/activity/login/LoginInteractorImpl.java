package id.co.vibe.vibe.ui.activity.login;

import javax.inject.Inject;

import id.co.vibe.vibe.api.VibeApi;
import id.co.vibe.vibe.api.request.LoginRequest;
import id.co.vibe.vibe.api.response.LoginResponse;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class LoginInteractorImpl implements LoginInteractor {
    private VibeApi vibeApi;

    @Inject
    LoginInteractorImpl(VibeApi vibeApi) {
        this.vibeApi = vibeApi;
    }

    @Override
    public Observable<LoginResponse> doEmailPasswordLogin(LoginRequest loginRequest) {
        return vibeApi.login(loginRequest)
                .subscribeOn(Schedulers.io());
    }
}
