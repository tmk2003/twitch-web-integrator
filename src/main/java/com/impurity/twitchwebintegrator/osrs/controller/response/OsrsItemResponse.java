package com.impurity.twitchwebintegrator.osrs.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.osrs.domain.OsrsItem;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class OsrsItemResponse {
    @JsonProperty("item")
    private OsrsItem item;
}
