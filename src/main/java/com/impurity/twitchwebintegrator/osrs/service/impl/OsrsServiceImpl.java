package com.impurity.twitchwebintegrator.osrs.service.impl;

import com.impurity.twitchwebintegrator.osrs.client.OsrsClient;
import com.impurity.twitchwebintegrator.osrs.client.response.OsrsApiItemResponse;
import com.impurity.twitchwebintegrator.osrs.domain.OsrsItem;
import com.impurity.twitchwebintegrator.osrs.domain.OsrsPlayer;
import com.impurity.twitchwebintegrator.osrs.exception.OsrsItemNotFoundException;
import com.impurity.twitchwebintegrator.osrs.exception.OsrsPlayerNotFoundException;
import com.impurity.twitchwebintegrator.osrs.service.OsrsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.impurity.twitchwebintegrator.osrs.builder.OsrsPlayerBuilder.buildPlayer;

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
    public OsrsItem getItem(Long itemId) {
        ResponseEntity<OsrsApiItemResponse> responseEntity = osrsClient.getItem(itemId);

        OsrsApiItemResponse osrsApiItemResponse = Optional
                .ofNullable(responseEntity.getBody())
                .orElseThrow(() -> new OsrsItemNotFoundException("No item body found"));

        return Optional
                .ofNullable(osrsApiItemResponse.getItem())
                .orElseThrow(() -> new OsrsItemNotFoundException("No item found"));
    }
}
