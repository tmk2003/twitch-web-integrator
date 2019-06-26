package com.impurity.twitchwebintegrator.domain.osrs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.domain.steam.SteamLibraryGame;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class OsrsSkill {
    @JsonProperty("rank")
    private Long rank;
    @JsonProperty("level")
    private Long level;
    @JsonProperty("experience")
    private Long experience;
}
