package com.impurity.twitchwebintegrator.model;

public class TwitchStream {
    private String id;
    private String userId;
    private String userName;
    private String gameId;
    private String[] communityIds;
    private String type;
    private String title;
    private Long viewerCount;
    private String startedAt;
    private String language;
    private String thumbnailUrl;
    private String[] tagIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String[] getCommunityIds() {
        return communityIds;
    }

    public void setCommunityIds(String[] communityIds) {
        this.communityIds = communityIds;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getViewerCount() {
        return viewerCount;
    }

    public void setViewerCount(Long viewerCount) {
        this.viewerCount = viewerCount;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String[] getTagIds() {
        return tagIds;
    }

    public void setTagIds(String[] tagIds) {
        this.tagIds = tagIds;
    }
}
