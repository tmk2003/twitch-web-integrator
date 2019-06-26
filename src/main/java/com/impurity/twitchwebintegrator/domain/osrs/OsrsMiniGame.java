package com.impurity.twitchwebintegrator.domain.osrs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class OsrsMiniGame {
    @JsonProperty("rank")
    private Long rank;
    @JsonProperty("score")
    private Long score;
}
