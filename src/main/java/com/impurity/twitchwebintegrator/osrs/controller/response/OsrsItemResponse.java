package com.impurity.twitchwebintegrator.osrs.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class OsrsItemResponse {
    @JsonProperty("item")
    private String item;
}
