package com.impurity.twitchwebintegrator.osrs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
