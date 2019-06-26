package com.impurity.twitchwebintegrator.domain.osrs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class OsrsPlayer {
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("skills")
    private OsrsSkills skills;
    @JsonProperty("scrolls")
    private OsrsScrolls scrolls;
    @JsonProperty("miniGames")
    private OsrsMiniGames miniGames;
}
