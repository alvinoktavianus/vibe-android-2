package id.co.vibe.vibe.ui.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.vibe.vibe.R;
import id.co.vibe.vibe.VibeApplication;
import id.co.vibe.vibe.base.BaseActivity;
import id.co.vibe.vibe.ui.fragment.home.HomeFragment;
import id.co.vibe.vibe.util.SharedPreferenceDB;
import timber.log.Timber;

public class MainActivity extends BaseActivity
        implements MainView, BottomNavigationViewEx.OnNavigationItemSelectedListener {

    public static final String TAG_HOME_FRAGMENT = "tag_home_fragment";
    public static final String TAG_PROFILE_FRAGMENT = "tag_profile_fragment";
    public static final String TAG_NOTIFICATION_FRAGMENT = "tag_notification_fragment";
    public static final String TAG_EXPLORE_FRAGMENT = "tag_explore_fragment";

    private boolean isLoggedIn;
    private HomeFragment homeFragment;

    @Inject
    SharedPreferenceDB db;

    @BindString(R.string.string_exit_vibe)
    String stringExitVibe;

    @BindString(R.string.string_no)
    String stringNo;

    @BindString(R.string.string_yes)
    String stringYes;

    @BindView(R.id.toolbar_profile_detail)
    Toolbar mToolbar;

    @BindView(R.id.bottom_navigation)
    BottomNavigationViewEx mBottonNavigation;

    @BindView(R.id.vibe_logo_image_view)
    AppCompatImageView mNavBarLogo;

    @BindView(R.id.title_main_activity)
    AppCompatTextView mNavbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        isLoggedIn = db.getBoolean(SharedPreferenceDB.DB_IS_LOGGED_IN);

        this.setupBottomNavigation();
        this.setupToolbar();

        if (savedInstanceState == null) {
            homeFragment = HomeFragment.newInstance();

            // Set the home fragment as the default activity
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_app_fragment, homeFragment, TAG_HOME_FRAGMENT).commit();
        } else {
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(TAG_HOME_FRAGMENT);
        }
    }

    @Override
    public void injectDependencies(VibeApplication application) {
        application.getMainSubComponent().inject(this);
    }

    @Override
    public void releaseSubComponents(VibeApplication application) {
        application.releaseMainSubComponent();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.action_home:
                mNavbarTitle.setVisibility(View.GONE);
                mNavBarLogo.setVisibility(View.VISIBLE);
                fragmentManager.beginTransaction().replace(R.id.main_app_fragment, homeFragment, TAG_HOME_FRAGMENT).commit();
                return true;
            case R.id.action_explore:
                mNavBarLogo.setVisibility(View.GONE);
                mNavbarTitle.setText(getString(R.string.main_menu_explore));
                mNavbarTitle.setVisibility(View.VISIBLE);
                Timber.i("Navigate user to explore fragment");
//                fragmentManager.beginTransaction().replace(R.id.main_app_fragment, exploreFragment, TAG_EXPLORE_FRAGMENT).commit();
                return true;
            case R.id.action_add:
                if (isLoggedIn) {
                    Timber.i("Navigate user to propagate vibe activity");
//                    startActivity(new Intent(MainActivity.this, PropagateVibeActivity.class));
                } else {
                    goToLoginActivity();
                    return false;
                }
                return false;
            case R.id.action_notification:
                if (isLoggedIn) {
                    mNavBarLogo.setVisibility(View.GONE);
                    mNavbarTitle.setText(getString(R.string.main_menu_notification));
                    mNavbarTitle.setVisibility(View.VISIBLE);
                    Timber.i("Navigate user to notification fragment");
//                    fragmentManager.beginTransaction().replace(R.id.main_app_fragment, notificationFragment, TAG_NOTIFICATION_FRAGMENT).commit();
                } else {
                    goToLoginActivity();
                    return false;
                }
                return true;
            case R.id.action_profile:
                if (isLoggedIn) {
                    mNavBarLogo.setVisibility(View.GONE);
                    mNavbarTitle.setText(getString(R.string.main_menu_profile));
                    mNavbarTitle.setVisibility(View.VISIBLE);
                    Timber.i("Navigate user to profile fragment");
//                    fragmentManager.beginTransaction().replace(R.id.main_app_fragment, profileFragment, TAG_PROFILE_FRAGMENT).commit();
                } else {
                    goToLoginActivity();
                    return false;
                }
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (!isLoggedIn) {
            new AlertDialog.Builder(this)
                    .setMessage(stringExitVibe)
                    .setPositiveButton(stringYes, (dialogInterface, i) -> goToLoginActivity())
                    .setNegativeButton(stringNo, null)
                    .create()
                    .show();
        } else {
            super.onBackPressed();
        }
    }

    private void setupBottomNavigation() {
        mBottonNavigation.enableAnimation(false);
        mBottonNavigation.enableItemShiftingMode(false);
        mBottonNavigation.enableShiftingMode(false);
        mBottonNavigation.setTextVisibility(false);
        mBottonNavigation.setOnNavigationItemSelectedListener(this);
    }

    private void setupToolbar() {
        mToolbar.setBackgroundResource(R.drawable.vibe_gradient_purple_toolbar);
        setSupportActionBar(mToolbar);
    }

}
