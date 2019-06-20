package com.impurity.twitchwebintegrator.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchUser {
    @JsonProperty("id")
    private String id;
    @JsonProperty("login")
    private String login;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("type")
    private String type;
    @JsonProperty("broadcaster_type")
    private String broadcasterType;
    @JsonProperty("description")
    private String description;
    @JsonProperty("profile_image_url")
    private String profileImageUrl;
    @JsonProperty("offline_image_url")
    private String offlineImageUrl;
    @JsonProperty("view_count")
    private Long viewCount;
}
