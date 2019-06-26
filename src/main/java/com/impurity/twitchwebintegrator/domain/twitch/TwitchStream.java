package com.impurity.twitchwebintegrator.domain.twitch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchStream {
    @JsonProperty("id")
    private String id;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("game_id")
    private String gameId;
    @JsonProperty("community_ids")
    private String[] communityIds;
    @JsonProperty("type")
    private String type;
    @JsonProperty("title")
    private String title;
    @JsonProperty("viewer_count")
    private Long viewerCount;
    @JsonProperty("started_at")
    private String startedAt;
    @JsonProperty("language")
    private String language;
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;
    @JsonProperty("tag_ids")
    private String[] tagIds;
}
