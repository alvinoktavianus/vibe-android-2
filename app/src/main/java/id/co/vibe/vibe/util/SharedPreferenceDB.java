package id.co.vibe.vibe.util;

import android.content.SharedPreferences;

public class SharedPreferenceDB {

    public static final String DB_ACCESS_TOKEN_KEY = "accessToken";
    public static final String DB_REFRESH_TOKEN_KEY = "refreshToken";

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

    public String get(String key) {
        return sharedPreferences.getString(key, null);
    }

    public boolean checkIfDataExistsOnDB() {
        return sharedPreferences.getAll() != null;
    }

}
