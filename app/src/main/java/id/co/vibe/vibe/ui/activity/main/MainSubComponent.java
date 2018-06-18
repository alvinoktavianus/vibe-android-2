package id.co.vibe.vibe.ui.activity.main;

import dagger.Subcomponent;

@Main
@Subcomponent
public interface MainSubComponent {
    void inject(MainActivity mainActivity);
}
