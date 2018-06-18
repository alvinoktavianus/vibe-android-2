package id.co.vibe.vibe.ui.activity.login;

import android.text.TextUtils;

import javax.inject.Inject;

import id.co.vibe.vibe.api.request.LoginRequest;
import id.co.vibe.vibe.base.BaseResponse;
import id.co.vibe.vibe.util.SharedPreferenceDB;
import id.co.vibe.vibe.util.Validation;
import id.co.vibe.vibe.util.VibeGsonSerializer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private LoginInteractor interactor;
    private Validation validation;
    private SharedPreferenceDB sharedPreferenceDB;
    private VibeGsonSerializer vibeGsonSerializer;

    @Inject
    LoginPresenterImpl(LoginInteractor interactor,
                       Validation validation,
                       SharedPreferenceDB sharedPreferenceDB,
                       VibeGsonSerializer vibeGsonSerializer) {
        this.interactor = interactor;
        this.validation = validation;
        this.sharedPreferenceDB = sharedPreferenceDB;
        this.vibeGsonSerializer = vibeGsonSerializer;
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
        String emailAddress = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        if (validation.isStringEmpty(emailAddress)) {
            view.showAlertDialogWithOkButtonAndNullClickListener(validation.getFieldIsRequiredMessage("Email address"));
            return;
        } else if (!validation.isEmailValid(emailAddress)) {
            view.showAlertDialogWithOkButtonAndNullClickListener(validation.getEmailIsNotValidMessage());
            return;
        }

        if (validation.isStringEmpty(password)) {
            view.showAlertDialogWithOkButtonAndNullClickListener(validation.getFieldIsRequiredMessage("Password"));
            return;
        }

        view.showProgressDialog();
        compositeDisposable.add(
                interactor.doEmailPasswordLogin(loginRequest)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(loginResponse -> {
                            view.hideProgressDialog();
                            view.showToast((String) TextUtils.concat("Welcome back, ", loginResponse.getData().getDisplayName()));
                            sharedPreferenceDB.save(SharedPreferenceDB.DB_ACCESS_TOKEN_KEY, loginResponse.getData().getAccessToken());
                            sharedPreferenceDB.save(SharedPreferenceDB.DB_REFRESH_TOKEN_KEY, loginResponse.getData().getRefreshToken());
                            view.goToMainActivity();
                        }, throwable -> {
                            if (throwable instanceof HttpException) {
                                view.hideProgressDialog();
                                String responseString = vibeGsonSerializer.convertHttpExceptionToJsonString(throwable);
                                BaseResponse response = vibeGsonSerializer.serializeBaseResponse(responseString);
                                view.showAlertDialogWithOkButtonAndNullClickListener(response.getMessage());
                            }
                        })
        );
    }

}
