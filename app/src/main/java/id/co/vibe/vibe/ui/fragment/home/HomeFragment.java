package id.co.vibe.vibe.ui.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import id.co.vibe.vibe.VibeApplication;
import id.co.vibe.vibe.base.BaseFragment;
import io.reactivex.disposables.CompositeDisposable;

public class HomeFragment extends BaseFragment implements HomeView {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    HomePresenter presenter;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter.bind(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.unbind();
        compositeDisposable.dispose();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter.setupLoggedInHomeFragment();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void injectDependencies(VibeApplication application) {
        application.getHomeSubComponent().inject(this);
    }

    @Override
    protected void releaseSubComponents(VibeApplication application) {
        application.releaseHomeSubComponent();
    }

}
