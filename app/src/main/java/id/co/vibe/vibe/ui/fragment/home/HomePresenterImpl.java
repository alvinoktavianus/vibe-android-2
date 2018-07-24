package id.co.vibe.vibe.ui.fragment.home;

import javax.inject.Inject;
import javax.inject.Named;

import id.co.vibe.vibe.api.request.RefreshTokenRequest;
import id.co.vibe.vibe.constant.ApiConstant;
import id.co.vibe.vibe.merge.HomeMergeResponse;
import id.co.vibe.vibe.provider.SchedulerProvider;
import id.co.vibe.vibe.util.SharedPreferenceDB;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

class HomePresenterImpl implements HomePresenter {
    private HomeView view;
    private CompositeDisposable compositeDisposable;
    private String accessToken;
    private String refreshToken;
    private RefreshTokenRequest refreshTokenRequest;
    private SchedulerProvider schedulerProvider;

    private SharedPreferenceDB db;
    private HomeInteractor interactor;

    @Inject
    HomePresenterImpl(SharedPreferenceDB db,
                      HomeInteractor interactor,
                      @Named("appId") String appId,
                      SchedulerProvider schedulerProvider,
                      CompositeDisposable compositeDisposable) {
        this.db = db;
        this.interactor = interactor;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;

        accessToken = db.getAccessToken();
        refreshToken = db.getString(SharedPreferenceDB.DB_REFRESH_TOKEN_KEY);
        refreshTokenRequest = new RefreshTokenRequest(refreshToken, appId);
    }

    @Override
    public void bind(HomeView view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        view = null;
        compositeDisposable.dispose();
    }

    @Override
    public void setupDefaultHomeFragment() {
    }

    @Override
    public void setupLoggedInHomeFragment() {
        Observable<HomeMergeResponse> combines = Observable.zip(
                interactor.getInThingData(accessToken, refreshTokenRequest),
                interactor.getLatestData(accessToken, ApiConstant.DEFAULT_PAGE, ApiConstant.DEFAULT_SIZE, refreshTokenRequest),
                interactor.getNeighboringData(accessToken, ApiConstant.DEFAULT_PAGE, ApiConstant.DEFAULT_SIZE, refreshTokenRequest),
                HomeMergeResponse::new
        );

        compositeDisposable.add(
                combines.observeOn(schedulerProvider.mainThread())
                        .subscribe(homeMergeResponse -> {
                            Timber.d("Returned data %s", homeMergeResponse.getInThingStory().toString());
                        })
        );
    }
}
