package id.co.vibe.vibe.util;

import android.content.SharedPreferences;
import android.text.TextUtils;

public class SharedPreferenceDB {

    public static final String DB_ACCESS_TOKEN_KEY = "accessToken";
    public static final String DB_REFRESH_TOKEN_KEY = "refreshToken";
    public static final String DB_IS_LOGGED_IN = "isLoggedIn";

    private SharedPreferences sharedPreferences;

    public SharedPreferenceDB(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void clearStorage() {
        sharedPreferences.edit().clear().apply();
    }

    public void save(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public void save(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public String getAccessToken() {
        String token = sharedPreferences.getString(DB_ACCESS_TOKEN_KEY, null);
        return token != null ? TextUtils.concat("Bearer", " ", token).toString() : null;
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public boolean checkIfDataExistsOnDB() {
        return sharedPreferences.getAll().size() > 0;
    }

}
