package id.co.vibe.vibe.api;

import id.co.vibe.vibe.api.request.LoginRequest;
import id.co.vibe.vibe.api.request.RefreshTokenRequest;
import id.co.vibe.vibe.api.response.InThingStoryResponse;
import id.co.vibe.vibe.api.response.LoginResponse;
import id.co.vibe.vibe.api.response.RefreshTokenResponse;
import id.co.vibe.vibe.api.response.StoryResponse;
import id.co.vibe.vibe.base.BaseResponse;
import id.co.vibe.vibe.constant.ApiConstant;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by alvinoktavianus (https://www.linkedin.com/in/alvinoktavianus)
 * on 6/17/2018
 */

public interface VibeApi {

    @POST(ApiConstant.API_LOGIN)
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST(ApiConstant.API_REFRESH_TOKEN)
    Observable<RefreshTokenResponse> postRefresh(@Body RefreshTokenRequest refreshTokenRequest);

    @GET(ApiConstant.API_NEIGHBORING)
    Observable<StoryResponse> getNeighboringStory(@Header("Authorization") String accessToken,
                                                  @Query("page") int page,
                                                  @Query("size") int size);

    @GET(ApiConstant.API_STORIES)
    Observable<StoryResponse> getLatestStory(@Header("Authorization") String accessToken,
                                             @Query("page") int page,
                                             @Query("size") int size);

    @GET(ApiConstant.API_IN_THING)
    Observable<InThingStoryResponse> getInThingStory(@Header("Authorization") String accessToken);

}
