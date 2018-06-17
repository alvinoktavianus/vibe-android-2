package id.co.vibe.vibe.ui.activity.login;

import javax.inject.Inject;

import id.co.vibe.vibe.api.request.LoginRequest;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private LoginInteractor interactor;

    @Inject
    LoginPresenterImpl(LoginInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void bind(LoginView view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        view = null;
        compositeDisposable.dispose();
    }

    @Override
    public void handleCheckInButton(LoginRequest loginRequest) {
        compositeDisposable.add(interactor.doEmailPasswordLogin(loginRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                }, throwable -> {
                })
        );
    }

}
