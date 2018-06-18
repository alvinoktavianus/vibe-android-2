package id.co.vibe.vibe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InThing {

    String id;
    Boolean published;
    Boolean active;
    InThing.User user;
    String cover;
    String title;
    Long comments;
    Long likes;
    Boolean isLiked;
    Boolean isCommentable;

    @Data
    @AllArgsConstructor
    public static class User {
        String id;
        String profilePicture;
        String username;
        String displayName;
    }

}
