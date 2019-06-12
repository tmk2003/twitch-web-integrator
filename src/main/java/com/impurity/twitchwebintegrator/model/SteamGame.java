package com.impurity.twitchwebintegrator.model;

import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class SteamGame {
    private Long appId;
    private String name;
    private Long playtimeForever;
    private String imgIconUrl;
    private String imgLogoUrl;
    private Boolean hasCommunityVisibleStats;
}
