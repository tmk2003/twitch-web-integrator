package com.impurity.twitchwebintegrator.service.impl;

import com.impurity.twitchwebintegrator.client.OsrsClient;
import com.impurity.twitchwebintegrator.client.response.steam.SteamApiLibraryResponse;
import com.impurity.twitchwebintegrator.exception.steam.SteamLibraryNotFoundException;
import com.impurity.twitchwebintegrator.service.OsrsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author tmk2003
 */
@Slf4j
@Service
public class OsrsServiceImpl implements OsrsService {

    @Autowired
    private OsrsClient osrsClient;

    @Override
    public String getPlayer(String playerName) {
        ResponseEntity<String> responseEntity = osrsClient.getPlayer(playerName);

        String osrsApiResponse = Optional
                .ofNullable(responseEntity.getBody())
                .orElseThrow(() -> new RuntimeException("No player body found"));


        return osrsClient.getPlayer(playerName).getBody();
    }

    @Override
    public String getItem(Long itemId) {
        return osrsClient.getItem(itemId).getBody();
    }
}
