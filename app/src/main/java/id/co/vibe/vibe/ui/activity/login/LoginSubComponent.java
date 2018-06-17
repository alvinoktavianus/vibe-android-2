package id.co.vibe.vibe.ui.activity.login;

import dagger.Subcomponent;

@Login
@Subcomponent(modules = {LoginModule.class})
public interface LoginSubComponent {
    void inject(LoginActivity loginActivity);
}
