package id.co.vibe.vibe;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

import id.co.vibe.vibe.module.ApplicationModule;
import id.co.vibe.vibe.module.NetworkModule;
import id.co.vibe.vibe.ui.activity.login.LoginModule;
import id.co.vibe.vibe.ui.activity.login.LoginSubComponent;
import id.co.vibe.vibe.ui.activity.main.MainSubComponent;
import id.co.vibe.vibe.ui.fragment.home.HomeModule;
import id.co.vibe.vibe.ui.fragment.home.HomeSubComponent;
import timber.log.Timber;


/**
 * Created by alvinoktavianus (https://www.linkedin.com/in/alvinoktavianus)
 * on 6/17/2018
 */

public class VibeApplication extends Application {

    private static ApplicationComponent component;
    private LoginSubComponent loginSubComponent;
    private MainSubComponent mainSubComponent;
    private HomeSubComponent homeSubComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initTimber();
        this.initLeakCanary();
        component = createComponent();
    }

    public static ApplicationComponent getComponent() {
        return component;
    }

    public static VibeApplication get(Context context) {
        return (VibeApplication) context.getApplicationContext();
    }

    public void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public void initLeakCanary() {
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }

    public ApplicationComponent createComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public LoginSubComponent getLoginSubComponent() {
        if (loginSubComponent == null)
            createLoginSubComponent();
        return loginSubComponent;
    }

    public LoginSubComponent createLoginSubComponent() {
        loginSubComponent = component.plus(new LoginModule());
        return loginSubComponent;
    }

    public void releaseLoginSubComponent() {
        loginSubComponent = null;
    }

    public MainSubComponent getMainSubComponent() {
        if (mainSubComponent == null)
            createMainSubComponent();
        return mainSubComponent;
    }

    public MainSubComponent createMainSubComponent() {
        mainSubComponent = component.plus();
        return mainSubComponent;
    }

    public void releaseMainSubComponent() {
        mainSubComponent = null;
    }

    public HomeSubComponent getHomeSubComponent() {
        if (homeSubComponent == null)
            createHomeSubComponent();
        return homeSubComponent;
    }

    public HomeSubComponent createHomeSubComponent() {
        homeSubComponent = component.plus(new HomeModule());
        return homeSubComponent;
    }

    public void releaseHomeSubComponent() {
        homeSubComponent = null;
    }

}
