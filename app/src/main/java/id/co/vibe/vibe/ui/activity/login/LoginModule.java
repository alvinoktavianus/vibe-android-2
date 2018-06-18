package id.co.vibe.vibe.ui.activity.login;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    @Login
    LoginPresenter providesLoginPresenter(LoginPresenterImpl presenter) {
        return presenter;
    }

}
