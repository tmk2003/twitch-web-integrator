package com.impurity.twitchwebintegrator.config;

import com.impurity.twitchwebintegrator.client.TwitchClient;
import com.impurity.twitchwebintegrator.properties.TwitchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tmk2003
 */
@Configuration
public class TwitchConfig {
    @Bean
    public TwitchClient getTwitchClient(
            final TwitchProperties twitchProperties
    ) {
        return new TwitchClient(twitchProperties);
    }

}
