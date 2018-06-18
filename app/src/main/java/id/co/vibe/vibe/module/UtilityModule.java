package id.co.vibe.vibe.module;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.co.vibe.vibe.util.SharedPreferenceDB;
import id.co.vibe.vibe.util.Validation;
import id.co.vibe.vibe.util.VibeGsonSerializer;

@Module
public class UtilityModule {

    @Provides
    @Singleton
    SharedPreferenceDB providesSharedPreferenceDB(SharedPreferences sharedPreferences) {
        return new SharedPreferenceDB(sharedPreferences);
    }

    @Provides
    @Singleton
    VibeGsonSerializer providesVibeGsonSerializer(Gson gson) {
        return new VibeGsonSerializer(gson);
    }

    @Provides
    @Singleton
    Validation providesValidation() {
        return new Validation();
    }

}
