package id.co.vibe.vibe;

import javax.inject.Singleton;

import dagger.Component;
import id.co.vibe.vibe.module.ApplicationModule;
import id.co.vibe.vibe.module.NetworkModule;
import id.co.vibe.vibe.ui.activity.login.LoginModule;
import id.co.vibe.vibe.ui.activity.login.LoginSubComponent;

/**
 * Created by alvinoktavianus (https://www.linkedin.com/in/alvinoktavianus)
 * on 6/17/2018
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
})
public interface ApplicationComponent {
    LoginSubComponent plus(LoginModule loginModule);
}
