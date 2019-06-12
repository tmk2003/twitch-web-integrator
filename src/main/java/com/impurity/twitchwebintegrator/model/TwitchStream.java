package com.impurity.twitchwebintegrator.model;

import lombok.Data;

/**
 * @author tmk2003
 */
@Data
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
}
