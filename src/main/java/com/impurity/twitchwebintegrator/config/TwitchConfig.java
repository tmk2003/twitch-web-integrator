package com.impurity.twitchwebintegrator.config;

import com.impurity.twitchwebintegrator.client.TwitchClient;
import com.impurity.twitchwebintegrator.properties.TwitchProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tmk2003
 */
@Configuration
public class TwitchConfig {

    @Autowired
    private TwitchProperties _twitchProperties;

    @Bean
    public TwitchClient getTwitchClient() {
        return new TwitchClient(_twitchProperties);
    }

}
