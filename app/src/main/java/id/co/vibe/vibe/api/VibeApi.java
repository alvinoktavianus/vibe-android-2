package id.co.vibe.vibe.api;

import id.co.vibe.vibe.api.request.LoginRequest;
import id.co.vibe.vibe.api.response.LoginResponse;
import id.co.vibe.vibe.constant.Constant;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by alvinoktavianus (https://www.linkedin.com/in/alvinoktavianus)
 * on 6/17/2018
 */

public interface VibeApi {

    @POST(Constant.API_LOGIN)
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);

}
