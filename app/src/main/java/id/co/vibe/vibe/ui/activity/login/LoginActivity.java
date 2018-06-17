package id.co.vibe.vibe.ui.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;

import com.crashlytics.android.Crashlytics;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.vibe.vibe.R;
import id.co.vibe.vibe.VibeApplication;
import id.co.vibe.vibe.base.BaseActivity;

/**
 * Created by alvinoktavianus (https://www.linkedin.com/in/alvinoktavianus)
 * on 6/17/2018
 */

public class LoginActivity extends BaseActivity implements LoginView {

    @Inject
    LoginPresenter presenter;

    @BindString(R.string.string_loading)
    String stringLoading;

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
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
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
    }

    @Override
    @OnClick(R.id.button_check_in_with_google)
    public void onClickCheckInWithGoogle() {
        Crashlytics.getInstance().crash();
    }

}
