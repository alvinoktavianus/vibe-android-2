package id.co.vibe.vibe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LatestAndNeighboring {

    String id;
    Boolean published;
    Boolean active;
    LatestAndNeighboring.User user;
    String cover;
    String title;
    Long comments;
    Long likes;
    Boolean isLiked;
    Boolean isCommentable;

    @AllArgsConstructor
    @Data
    public static class User {
        String id;
        String profilePicture;
        String username;
        String displayName;
    }

}
