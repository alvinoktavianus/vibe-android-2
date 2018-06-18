package id.co.vibe.vibe.merge;

import java.util.List;

import id.co.vibe.vibe.model.InThing;
import id.co.vibe.vibe.model.LatestAndNeighboring;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HomeMergeResponse {
    InThing inThingStory;
    List<LatestAndNeighboring> latestStories;
    List<LatestAndNeighboring> neighboringStories;
}
