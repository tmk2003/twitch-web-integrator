package com.impurity.twitchwebintegrator.config;

import com.impurity.twitchwebintegrator.client.SteamClient;
import com.impurity.twitchwebintegrator.properties.SteamProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tmk2003
 */
@Configuration
public class SteamConfig {

    @Autowired
    private SteamProperties _steamProperties;

    @Bean
    public SteamClient getSteamClient() {
        return new SteamClient(_steamProperties);
    }

}
