package id.co.vibe.vibe;

import javax.inject.Singleton;

import dagger.Component;
import id.co.vibe.vibe.module.ApplicationModule;
import id.co.vibe.vibe.module.NetworkModule;
import id.co.vibe.vibe.module.UtilityModule;
import id.co.vibe.vibe.ui.activity.login.LoginModule;
import id.co.vibe.vibe.ui.activity.login.LoginSubComponent;
import id.co.vibe.vibe.ui.activity.main.MainSubComponent;
import id.co.vibe.vibe.ui.fragment.home.HomeModule;
import id.co.vibe.vibe.ui.fragment.home.HomeSubComponent;

/**
 * Created by alvinoktavianus (https://www.linkedin.com/in/alvinoktavianus)
 * on 6/17/2018
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        UtilityModule.class,
})
public interface ApplicationComponent {
    LoginSubComponent plus(LoginModule loginModule);

    MainSubComponent plus();

    HomeSubComponent plus(HomeModule homeModule);
}
