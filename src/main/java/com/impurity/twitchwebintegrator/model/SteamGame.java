package com.impurity.twitchwebintegrator.model;

/**
 * @author tmk2003
 */
public class SteamGame {
    private Long appId;
    private String name;
    private Long playtimeForever;
    private String imgIconUrl;
    private String imgLogoUrl;
    private Boolean hasCommunityVisibleStats;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPlaytimeForever() {
        return playtimeForever;
    }

    public void setPlaytimeForever(Long playtimeForever) {
        this.playtimeForever = playtimeForever;
    }

    public String getImgIconUrl() {
        return imgIconUrl;
    }

    public void setImgIconUrl(String imgIconUrl) {
        this.imgIconUrl = imgIconUrl;
    }

    public String getImgLogoUrl() {
        return imgLogoUrl;
    }

    public void setImgLogoUrl(String imgLogoUrl) {
        this.imgLogoUrl = imgLogoUrl;
    }

    public Boolean getHasCommunityVisibleStats() {
        return hasCommunityVisibleStats;
    }

    public void setHasCommunityVisibleStats(Boolean hasCommunityVisibleStats) {
        this.hasCommunityVisibleStats = hasCommunityVisibleStats;
    }
}
