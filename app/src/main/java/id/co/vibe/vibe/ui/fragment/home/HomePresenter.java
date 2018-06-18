package id.co.vibe.vibe.ui.fragment.home;

import id.co.vibe.vibe.base.BasePresenter;

interface HomePresenter extends BasePresenter<HomeView> {
    void setupDefaultHomeFragment();

    void setupLoggedInHomeFragment();
}
