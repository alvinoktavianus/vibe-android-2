package id.co.vibe.vibe.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.vibe.vibe.base.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InThingStoryResponse extends BaseResponse {

    InThingStoryResponse.Data data;

    @Getter
    @NoArgsConstructor
    public static class Data {
        @SerializedName("_id")
        @Expose
        String id;
        List<String> tags = null;
        Boolean published;
        Boolean isCommentable;
        Boolean active;
        InThingStoryResponse.User user;
        String cover;
        String title;
        String publishedAt;
        String createdAt;
        String updatedAt;
        @SerializedName("__v")
        @Expose
        Integer v;
        Integer comments;
        Integer likes;
    }

    @Getter
    @NoArgsConstructor
    public static class User {
        @SerializedName("_id")
        @Expose
        String id;
        String profilePicture;
        String username;
        String displayName;
    }

}
