package com.impurity.twitchwebintegrator.domain.steam;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class SteamLibraryGame {
    @JsonProperty("appid")
    private Long appId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("playtime_forever")
    private Long playtimeForever;
    @JsonProperty("img_icon_url")
    private String imgIconUrl;
    @JsonProperty("img_logo_url")
    private String imgLogoUrl;
    @JsonProperty("has_community_visible_stats")
    private Boolean hasCommunityVisibleStats;
}
