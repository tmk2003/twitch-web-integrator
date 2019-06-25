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
    @Bean
    public SteamClient getSteamClient(
            final SteamProperties steamProperties
    ) {
        return new SteamClient(steamProperties);
    }

}
