package com.impurity.twitchwebintegrator.service.impl;

import com.impurity.twitchwebintegrator.client.SteamClient;
import com.impurity.twitchwebintegrator.client.response.SteamServerLibraryResponse;
import com.impurity.twitchwebintegrator.domain.SteamLibrary;
import com.impurity.twitchwebintegrator.exception.steam.SteamLibraryNotFoundException;
import com.impurity.twitchwebintegrator.service.SteamService;
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
    public SteamLibrary getGameLibrary(String steamProfileID) {
        ResponseEntity<SteamServerLibraryResponse> responseEntity = steamClient.getLibrary(steamProfileID);
        SteamServerLibraryResponse steamServerLibraryResponse = responseEntity.getBody();
        SteamLibrary steamLibrary = Optional
                .ofNullable(steamServerLibraryResponse.getResponse())
                .orElseThrow(() -> new SteamLibraryNotFoundException("No Library Found"));
        Arrays.stream(steamLibrary.getGames()).forEach(
                steamLibraryGame -> {
                    Long appId = steamLibraryGame.getAppId();
                    String imgIconUrl = steamLibraryGame.getImgIconUrl();
                    String imgLogoUrl = steamLibraryGame.getImgLogoUrl();
                    steamLibraryGame.setImgIconUrl(steamClient.imageHashToUrl(appId, imgIconUrl));
                    steamLibraryGame.setImgIconUrl(steamClient.imageHashToUrl(appId, imgLogoUrl));
                }
        );
        return steamLibrary;
    }

    @Override
    public Long getGameLibraryAmount(String steamProfileID) {
        return getGameLibrary(steamProfileID).getGameCount();
    }

}
