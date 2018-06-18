package id.co.vibe.vibe.ui.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;

import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.vibe.vibe.R;
import id.co.vibe.vibe.VibeApplication;
import id.co.vibe.vibe.api.request.LoginRequest;
import id.co.vibe.vibe.base.BaseActivity;
import id.co.vibe.vibe.util.SharedPreferenceDB;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by alvinoktavianus (https://www.linkedin.com/in/alvinoktavianus)
 * on 6/17/2018
 */

public class LoginActivity extends BaseActivity implements LoginView {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String deviceToken;

    @Inject
    LoginPresenter presenter;

    @Inject
    SharedPreferenceDB db;

    @Inject
    @Named("appId")
    String appId;

    @BindView(R.id.input_layout_email)
    TextInputLayout mEmailLayout;

    @BindView(R.id.input_layout_password)
    TextInputLayout mPasswordLayout;

    @BindView(R.id.edit_text_login_email)
    AppCompatEditText mEditTextEmail;

    @BindView(R.id.edit_text_login_password)
    AppCompatEditText mEditTextPassword;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.bind(this);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.unbind();
        compositeDisposable.dispose();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (db.checkIfDataExistsOnDB()) {
            super.goToMainActivity();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        deviceToken = FirebaseInstanceId.getInstance().getToken();
    }

    @Override
    public void injectDependencies(VibeApplication application) {
        application.getLoginSubComponent().inject(this);
    }

    @Override
    public void releaseSubComponents(VibeApplication application) {
        application.releaseLoginSubComponent();
    }

    @Override
    @OnClick(R.id.button_check_in)
    public void onClickCheckIn() {
        LoginRequest loginRequest = new LoginRequest(
                mEditTextEmail.getText().toString().trim(),
                mEditTextPassword.getText().toString().trim(),
                appId,
                deviceToken
        );
        presenter.handleCheckInButton(loginRequest);
    }

    @Override
    @OnClick(R.id.button_check_in_with_google)
    public void onClickCheckInWithGoogle() {
    }

    @Override
    @OnClick(R.id.button_check_in_with_facebook)
    public void onClickCheckInWithFacebook() {

    }

    @Override
    @OnClick(R.id.button_register_new_account)
    public void onClickRegister() {

    }

    @Override
    @OnClick(R.id.button_browse_as_tourist)
    public void onClickBrowseAsTourist() {
        super.goToMainActivity();
    }

    @Override
    @OnClick(R.id.button_forgot_password)
    public void onClickForgotPassword() {

    }

}
