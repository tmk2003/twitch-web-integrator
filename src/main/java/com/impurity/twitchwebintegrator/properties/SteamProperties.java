package com.impurity.twitchwebintegrator.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "steam")
public class SteamProperties {

    private String key;
    private String getLibraryUrl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getGetLibraryUrl() {
        return getLibraryUrl;
    }

    public void setGetLibraryUrl(String getLibraryUrl) {
        this.getLibraryUrl = getLibraryUrl;
    }
}
