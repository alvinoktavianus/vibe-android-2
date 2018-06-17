package id.co.vibe.vibe.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import id.co.vibe.vibe.VibeApplication;
import id.co.vibe.vibe.ui.activity.login.LoginActivity;

/**
 * Created by alvinoktavianus (https://www.linkedin.com/in/alvinoktavianus)
 * on 6/17/2018
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(VibeApplication.get(this));
    }

    @Override
    public void finish() {
        super.finish();
        releaseSubComponents(VibeApplication.get(this));
    }

    public abstract void injectDependencies(VibeApplication application);

    public abstract void releaseSubComponents(VibeApplication application);

    protected void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        this.finish();
    }

}
