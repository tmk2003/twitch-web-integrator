package com.impurity.twitchwebintegrator.model;

import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchUser {
    private String id;
    private String login;
    private String displayName;
    private String type;
    private String broadcasterType;
    private String description;
    private String profileImageUrl;
    private String offlineImageUrl;
    private Long viewCount;
}
