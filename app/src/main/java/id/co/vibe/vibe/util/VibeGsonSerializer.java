package id.co.vibe.vibe.util;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;

import javax.inject.Inject;

import id.co.vibe.vibe.base.BaseResponse;
import retrofit2.HttpException;

public class VibeGsonSerializer {

    private Gson gson;

    @Inject
    public VibeGsonSerializer(Gson gson) {
        this.gson = gson;
    }

    public BaseResponse serializeBaseResponse(String json) {
        return gson.fromJson(json, BaseResponse.class);
    }

    public String convertHttpExceptionToJsonString(Throwable throwable) throws IOException {
        return Objects.requireNonNull(((HttpException) throwable).response().errorBody()).string();
    }

}
