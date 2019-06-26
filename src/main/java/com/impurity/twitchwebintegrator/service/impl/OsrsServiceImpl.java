package com.impurity.twitchwebintegrator.service.impl;

import com.impurity.twitchwebintegrator.client.OsrsClient;
import com.impurity.twitchwebintegrator.domain.osrs.OsrsPlayer;
import com.impurity.twitchwebintegrator.exception.osrs.OsrsPlayerNotFoundException;
import com.impurity.twitchwebintegrator.service.OsrsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.impurity.twitchwebintegrator.builder.osrs.OsrsPlayerBuilder.buildPlayer;

/**
 * @author tmk2003
 */
@Slf4j
@Service
public class OsrsServiceImpl implements OsrsService {

    @Autowired
    private OsrsClient osrsClient;

    @Override
    public OsrsPlayer getPlayer(String playerName) {
        ResponseEntity<String> responseEntity = osrsClient.getPlayer(playerName);

        String osrsApiResponse = Optional
                .ofNullable(responseEntity.getBody())
                .orElseThrow(() -> new OsrsPlayerNotFoundException("No player body found"));


        return buildPlayer(playerName, "TODO", osrsApiResponse);
    }

    @Override
    public String getItem(Long itemId) {
        return osrsClient.getItem(itemId).getBody();
    }
}
