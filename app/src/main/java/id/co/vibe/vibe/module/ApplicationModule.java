package id.co.vibe.vibe.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.co.vibe.vibe.BuildConfig;
import id.co.vibe.vibe.provider.AppSchedulerProvider;
import id.co.vibe.vibe.provider.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by alvinoktavianus (https://www.linkedin.com/in/alvinoktavianus)
 * on 6/17/2018
 */

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return this.mApplication;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    @Named("appId")
    String providesAppId() {
        return BuildConfig.APP_ID;
    }

    @Provides
    @Singleton
    @Named("isDebug")
    boolean providesIsDebug() {
        return BuildConfig.DEBUG;
    }

    @Provides
    @Singleton
    @Named("serverUrl")
    String providesServerUrl() {
        return BuildConfig.SERVER_URL;
    }

    @Provides
    @Singleton
    FirebaseAnalytics providesFirebaseAnalytics(Application application) {
        return FirebaseAnalytics.getInstance(application);
    }

    @Provides
    @Singleton
    SchedulerProvider providesSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    CompositeDisposable providesCompositeDisposable() {
        return new CompositeDisposable();
    }

}
