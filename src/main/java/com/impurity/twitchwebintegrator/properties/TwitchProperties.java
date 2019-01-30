package com.impurity.twitchwebintegrator.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "twitch")
public class TwitchProperties {

    private String getUserUrl;
    private String getFollowersUrl;
    private String getStreamUrl;
    private String clientId;

    public String getGetUserUrl() {
        return getUserUrl;
    }

    public void setGetUserUrl(String getUserUrl) {
        this.getUserUrl = getUserUrl;
    }

    public String getGetFollowersUrl() {
        return getFollowersUrl;
    }

    public void setGetFollowersUrl(String getFollowersUrl) {
        this.getFollowersUrl = getFollowersUrl;
    }

    public String getGetStreamUrl() {
        return getStreamUrl;
    }

    public void setGetStreamUrl(String getStreamUrl) {
        this.getStreamUrl = getStreamUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
