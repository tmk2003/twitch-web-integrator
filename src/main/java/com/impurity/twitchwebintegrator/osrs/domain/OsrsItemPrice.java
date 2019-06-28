package com.impurity.twitchwebintegrator.osrs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class OsrsItemPrice {
    @JsonProperty("trend")
    private String trend;
    @JsonProperty("price")
    private String price;
}
