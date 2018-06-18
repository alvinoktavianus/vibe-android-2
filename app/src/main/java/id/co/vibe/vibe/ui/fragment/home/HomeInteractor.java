package id.co.vibe.vibe.ui.fragment.home;

import java.util.List;

import id.co.vibe.vibe.api.request.RefreshTokenRequest;
import id.co.vibe.vibe.base.BaseResponse;
import id.co.vibe.vibe.model.InThing;
import id.co.vibe.vibe.model.LatestAndNeighboring;
import io.reactivex.Observable;

interface HomeInteractor {
    Observable<List<LatestAndNeighboring>> getLatestData(String accessToken, int page, int size, RefreshTokenRequest refreshTokenRequest);

    Observable<List<LatestAndNeighboring>> getNeighboringData(String accessToken, int page, int size, RefreshTokenRequest refreshTokenRequest);

    Observable<BaseResponse> likeStory(String accessToken, String storyId, RefreshTokenRequest refreshTokenRequest);

    Observable<BaseResponse> unlikeStory(String accessToken, String storyId, RefreshTokenRequest refreshTokenRequest);

    Observable<InThing> getInThingData(String accessToken, RefreshTokenRequest refreshTokenRequest);
}
