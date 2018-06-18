package id.co.vibe.vibe.ui.activity.main;

import android.os.Bundle;

import id.co.vibe.vibe.R;
import id.co.vibe.vibe.VibeApplication;
import id.co.vibe.vibe.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void injectDependencies(VibeApplication application) {

    }

    @Override
    public void releaseSubComponents(VibeApplication application) {

    }

}
