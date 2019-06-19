package com.impurity.twitchwebintegrator.domain;

import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchFollower {
    private String fromId;
    private String fromName;
    private String toId;
    private String toName;
    private String followedAt;
}
