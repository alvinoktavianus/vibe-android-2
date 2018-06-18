package id.co.vibe.vibe.ui.fragment.home;

import dagger.Subcomponent;

@Home
@Subcomponent(modules = {HomeModule.class})
public interface HomeSubComponent {
    void inject(HomeFragment homeFragment);
}
