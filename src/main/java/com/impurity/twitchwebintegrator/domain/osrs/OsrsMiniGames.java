package com.impurity.twitchwebintegrator.domain.osrs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class OsrsMiniGames {
    @JsonProperty("bountyHunter")
    private OsrsMiniGame bountyHunter;
    @JsonProperty("bountyHunterRogues")
    private OsrsMiniGame bountyHunterRogues;
    @JsonProperty("lastManStanding")
    private OsrsMiniGame lastManStanding;
}
