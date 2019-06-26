package com.impurity.twitchwebintegrator.service.impl;

import com.impurity.twitchwebintegrator.client.SteamClient;
import com.impurity.twitchwebintegrator.client.response.SteamApiLibraryResponse;
import com.impurity.twitchwebintegrator.domain.steam.SteamLibrary;
import com.impurity.twitchwebintegrator.domain.steam.SteamLibraryGame;
import com.impurity.twitchwebintegrator.exception.steam.SteamLibraryNotFoundException;
import com.impurity.twitchwebintegrator.service.SteamService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author tmk2003
 */
@Slf4j
@Service
public class SteamServiceImpl implements SteamService {

    @Autowired
    private SteamClient steamClient;

    @Override
    public SteamLibrary getGameLibrary(@NonNull String steamProfileID) {
        ResponseEntity<SteamApiLibraryResponse> responseEntity = steamClient.getLibrary(steamProfileID);

        SteamApiLibraryResponse steamApiLibraryResponse = Optional
                .ofNullable(responseEntity.getBody())
                .orElseThrow(() -> new SteamLibraryNotFoundException("No library body found"));

        SteamLibrary steamLibrary = Optional
                .ofNullable(steamApiLibraryResponse.getResponse())
                .orElseThrow(() -> new SteamLibraryNotFoundException("No library found"));

        steamLibrary.setGames(Optional.ofNullable(steamLibrary.getGames()).orElse(new SteamLibraryGame[0]));
        Arrays.stream(steamLibrary.getGames()).forEach(
                game -> {
                    game.setImgIconUrl(imageHashToUrl(game.getAppId(), game.getImgIconUrl()));
                    game.setImgLogoUrl(imageHashToUrl(game.getAppId(), game.getImgLogoUrl()));
                }
        );
        steamLibrary.setGameCount(Optional.ofNullable(steamLibrary.getGameCount()).orElse(0L));

        return steamLibrary;
    }

    @Override
    public Long getGameLibraryTotal(@NonNull String steamProfileID) {
        return getGameLibrary(steamProfileID).getGameCount();
    }

    /**
     * Convert a app id & image hash to an image url
     * @param appId the app id for the steam game
     * @param imageHash the image hash for the steam game
     * @return the proper url to get the image
     */
    private String imageHashToUrl(@NonNull final Long appId, @NonNull final String imageHash) {
        return "http://media.steampowered.com/steamcommunity/public/images/apps/" + appId + "/" + imageHash + ".jpg";
    }

}
