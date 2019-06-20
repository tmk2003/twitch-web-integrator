package com.impurity.twitchwebintegrator.service.impl;

import com.impurity.twitchwebintegrator.client.SteamClient;
import com.impurity.twitchwebintegrator.client.response.SteamApiLibraryResponse;
import com.impurity.twitchwebintegrator.domain.SteamLibrary;
import com.impurity.twitchwebintegrator.exception.steam.SteamLibraryNotFoundException;
import com.impurity.twitchwebintegrator.service.SteamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
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
    public SteamLibrary getGameLibrary(@NotBlank String steamProfileID) {
        ResponseEntity<SteamApiLibraryResponse> responseEntity = steamClient.getLibrary(steamProfileID);

        SteamApiLibraryResponse steamApiLibraryResponse = Optional
                .ofNullable(responseEntity.getBody())
                .orElseThrow(() -> new SteamLibraryNotFoundException("No library body found"));

        SteamLibrary steamLibrary = Optional
                .ofNullable(steamApiLibraryResponse.getResponse())
                .orElseThrow(() -> new SteamLibraryNotFoundException("No library found"));

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
    public Long getGameLibraryAmount(@NotBlank String steamProfileID) {
        return Optional
                .ofNullable(getGameLibrary(steamProfileID).getGameCount())
                .orElseThrow(() -> new SteamLibraryNotFoundException("No library amount found"));
    }

}
