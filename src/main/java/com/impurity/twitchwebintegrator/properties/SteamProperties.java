package com.impurity.twitchwebintegrator.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tmk2003
 */
@Data
@Component
@ConfigurationProperties(prefix = "steam")
public class SteamProperties {
    private String key;
}
