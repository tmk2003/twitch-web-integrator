package com.impurity.twitchwebintegrator.domain.osrs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class OsrsScrolls {
    @JsonProperty("clueScrolls")
    private OsrsScroll clueScrolls;
    @JsonProperty("easyClueScrolls")
    private OsrsScroll easyClueScrolls;
    @JsonProperty("mediumClueScrolls")
    private OsrsScroll mediumClueScrolls;
    @JsonProperty("hardClueScrolls")
    private OsrsScroll hardClueScrolls;
    @JsonProperty("eliteClueScrolls")
    private OsrsScroll eliteClueScrolls;
    @JsonProperty("masterClueScrolls")
    private OsrsScroll masterClueScrolls;
}
