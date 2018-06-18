package id.co.vibe.vibe.ui.fragment.home;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    @Home
    @Provides
    HomePresenter providesHomePresenter(HomePresenterImpl presenter) {
        return presenter;
    }

    @Home
    @Provides
    HomeInteractor providesHomeInteractor(HomeInteractorImpl interactor) {
        return interactor;
    }

}
