package id.co.vibe.vibe.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.vibe.vibe.base.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoryResponse extends BaseResponse {

    Data data;

    @Getter
    @NoArgsConstructor
    public static class Data {
        List<Story> stories = null;
        Long total;
    }

    @Getter
    @NoArgsConstructor
    public static class Page {
        Text text;
        String image;
    }

    @Getter
    @NoArgsConstructor
    public static class Story {
        @SerializedName("_id")
        @Expose
        String id;
        User user;
        String cover;
        String title;
        List<String> tags = null;
        List<Page> pages = null;
        String createdAt;
        String publishedAt;
        Boolean active;
        Boolean published;
        Long comments;
        Long likes;
        Boolean liked;
        Boolean commentable;
    }

    @Getter
    @NoArgsConstructor
    public static class Text {
        String value;
        String color;
        String alignment;
        String font;
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

