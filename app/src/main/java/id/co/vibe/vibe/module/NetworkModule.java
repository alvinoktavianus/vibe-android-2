package id.co.vibe.vibe.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.co.vibe.vibe.BuildConfig;
import id.co.vibe.vibe.api.VibeApi;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alvinoktavianus (https://www.linkedin.com/in/alvinoktavianus)
 * on 6/17/2018
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    VibeApi providesVibeApi(Retrofit retrofit) {
        return retrofit.create(VibeApi.class);
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(HttpUrl httpUrl,
                              Converter.Factory converterFactory,
                              CallAdapter.Factory callAdapterFactory,
                              OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(httpUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    Converter.Factory providesGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    Gson providesGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    CallAdapter.Factory providesCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    HttpUrl providesEndPoint(@Named("serverUrl") String serverUrl) {
        return HttpUrl.parse(serverUrl);
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(httpLoggingInterceptor);
        return client.build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor providesHttpLoggingInterceptor(@Named("isDebug") boolean isDebug) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (isDebug) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
        return httpLoggingInterceptor;
    }

}
