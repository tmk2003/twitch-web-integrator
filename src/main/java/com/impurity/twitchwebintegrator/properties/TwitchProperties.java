package com.impurity.twitchwebintegrator.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tmk2003
 */
@Data
@Component
@ConfigurationProperties(prefix = "twitch")
public class TwitchProperties {
    private String clientId;
    private String clientSecret;
}
