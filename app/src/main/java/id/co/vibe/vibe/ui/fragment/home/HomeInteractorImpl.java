package id.co.vibe.vibe.ui.fragment.home;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import id.co.vibe.vibe.api.VibeApi;
import id.co.vibe.vibe.api.request.RefreshTokenRequest;
import id.co.vibe.vibe.api.response.InThingStoryResponse;
import id.co.vibe.vibe.api.response.StoryResponse;
import id.co.vibe.vibe.base.BaseResponse;
import id.co.vibe.vibe.constant.ApiConstant;
import id.co.vibe.vibe.model.InThing;
import id.co.vibe.vibe.model.LatestAndNeighboring;
import id.co.vibe.vibe.provider.SchedulerProvider;
import id.co.vibe.vibe.util.SharedPreferenceDB;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

class HomeInteractorImpl implements HomeInteractor {
    private VibeApi vibeApi;
    private SharedPreferenceDB db;
    private SchedulerProvider schedulerProvider;

    @Inject
    HomeInteractorImpl(VibeApi vibeApi,
                       SharedPreferenceDB db,
                       SchedulerProvider schedulerProvider) {
        this.vibeApi = vibeApi;
        this.db = db;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public Observable<List<LatestAndNeighboring>> getLatestData(String accessToken, int page, int size, RefreshTokenRequest refreshTokenRequest) {
        return vibeApi.getLatestStory(accessToken, page, size)
                .flatMap(response -> {
                    if (response.getCode().equals(HttpURLConnection.HTTP_UNAUTHORIZED)) {
                        return vibeApi.postRefresh(refreshTokenRequest)
                                .flatMap(refreshTokenResponse -> {
                                    String newAccessToken = refreshTokenResponse.getData().getAccessToken();
                                    String newRefreshToken = refreshTokenResponse.getData().getRefreshToken();

                                    db.save(SharedPreferenceDB.DB_ACCESS_TOKEN_KEY, newAccessToken);
                                    db.save(SharedPreferenceDB.DB_REFRESH_TOKEN_KEY, newRefreshToken);

                                    return vibeApi.getNeighboringStory(db.getAccessToken(), ApiConstant.DEFAULT_PAGE, ApiConstant.DEFAULT_SIZE)
                                            .map(this::transformStoryResponseToTarget)
                                            .subscribeOn(Schedulers.computation());
                                });
                    } else {
                        return Observable.just(transformStoryResponseToTarget(response));
                    }
                })
                .subscribeOn(schedulerProvider.computationThread());
    }

    @Override
    public Observable<List<LatestAndNeighboring>> getNeighboringData(String accessToken, int page, int size, RefreshTokenRequest refreshTokenRequest) {
        return vibeApi.getNeighboringStory(accessToken, page, size)
                .flatMap(storyResponse -> {
                    if (storyResponse.getCode().equals(HttpURLConnection.HTTP_UNAUTHORIZED)) {
                        return vibeApi.postRefresh(refreshTokenRequest)
                                .flatMap(refreshTokenResponse -> {
                                    String newAccessToken = refreshTokenResponse.getData().getAccessToken();
                                    String newRefreshToken = refreshTokenResponse.getData().getRefreshToken();

                                    db.save(SharedPreferenceDB.DB_ACCESS_TOKEN_KEY, newAccessToken);
                                    db.save(SharedPreferenceDB.DB_REFRESH_TOKEN_KEY, newRefreshToken);

                                    return vibeApi.getNeighboringStory(db.getAccessToken(), ApiConstant.DEFAULT_PAGE, ApiConstant.DEFAULT_SIZE)
                                            .map(this::transformStoryResponseToTarget)
                                            .subscribeOn(Schedulers.computation());
                                });
                    } else {
                        return Observable.just(transformStoryResponseToTarget(storyResponse));
                    }
                })
                .subscribeOn(schedulerProvider.computationThread());
    }

    @Override
    public Observable<BaseResponse> likeStory(String accessToken, String storyId, RefreshTokenRequest refreshTokenRequest) {
        return null;
    }

    @Override
    public Observable<BaseResponse> unlikeStory(String accessToken, String storyId, RefreshTokenRequest refreshTokenRequest) {
        return null;
    }

    @Override
    public Observable<InThing> getInThingData(String accessToken, RefreshTokenRequest refreshTokenRequest) {
        return vibeApi.getInThingStory(accessToken)
                .flatMap(inThingStoryResponse -> {
                    if (inThingStoryResponse.getCode().equals(HttpURLConnection.HTTP_UNAUTHORIZED)) {
                        return vibeApi.postRefresh(refreshTokenRequest)
                                .flatMap(refreshTokenResponse -> {
                                    String newAccessToken = refreshTokenResponse.getData().getAccessToken();
                                    String newRefreshToken = refreshTokenResponse.getData().getRefreshToken();

                                    db.save(SharedPreferenceDB.DB_ACCESS_TOKEN_KEY, newAccessToken);
                                    db.save(SharedPreferenceDB.DB_REFRESH_TOKEN_KEY, newRefreshToken);

                                    return vibeApi.getInThingStory(db.getAccessToken())
                                            .map(this::transformInThingResponseToTarget)
                                            .subscribeOn(Schedulers.computation());
                                });
                    } else {
                        return Observable.just(transformInThingResponseToTarget(inThingStoryResponse));
                    }
                })
                .subscribeOn(schedulerProvider.computationThread());
    }

    private InThing transformInThingResponseToTarget(InThingStoryResponse response) {
        InThing inThingData = new InThing();

        if (response.getData() != null) {
            InThingStoryResponse.Data data = response.getData();
            inThingData.setPublished(data.getPublished());
            inThingData.setLikes(Long.valueOf(data.getLikes()));
            inThingData.setActive(data.getActive());
            inThingData.setTitle(data.getTitle());
            inThingData.setComments(Long.valueOf(data.getComments()));
            inThingData.setUser(new InThing.User(
                    data.getUser().getId(),
                    data.getUser().getProfilePicture(),
                    data.getUser().getUsername(),
                    data.getUser().getDisplayName()
            ));
            inThingData.setId(data.getId());
            inThingData.setCover(data.getCover());
            inThingData.setIsCommentable(data.getIsCommentable());
            inThingData.setIsLiked(true); // TODO: must match with the API
        }

        return inThingData;
    }

    private List<LatestAndNeighboring> transformStoryResponseToTarget(StoryResponse response) {
        List<LatestAndNeighboring> list = new ArrayList<>();

        if (response.getData().getTotal() > 0) {
            for (StoryResponse.Story story : response.getData().getStories()) {
                LatestAndNeighboring data = new LatestAndNeighboring();
                data.setId(story.getId());
                data.setCover(story.getCover());
                data.setLikes(story.getLikes());
                data.setActive(story.getActive());
                data.setUser(new LatestAndNeighboring.User(
                        story.getUser().getId(),
                        story.getUser().getProfilePicture(),
                        story.getUser().getUsername(),
                        story.getUser().getDisplayName()
                ));
                data.setTitle(story.getTitle());
                data.setPublished(story.getPublished());
                data.setComments(story.getComments());
                data.setIsLiked(story.getLiked());
                data.setIsCommentable(story.getCommentable());
                list.add(data);
            }
        }

        return list;
    }

}
