package com.impurity.twitchwebintegrator.test.utils;

import com.impurity.twitchwebintegrator.domain.SteamLibraryGame;

/**
 * @author tmk2003
 */
public class SteamFactory {
    public static SteamLibraryGame[] getValidSteamGameArray(int amount) {
        SteamLibraryGame[] steamGames = new SteamLibraryGame[amount];
        for(int i = 0; i < amount; i++) {
            steamGames[i] = getValidSteamGame();
        }
        return steamGames;
    }

    public static SteamLibraryGame getValidSteamGame() {
        SteamLibraryGame steamGame = new SteamLibraryGame();
        steamGame.setAppId(123L);
        steamGame.setHasCommunityVisibleStats(true);
        steamGame.setImgIconUrl("");
        steamGame.setImgLogoUrl("");
        steamGame.setName("");
        steamGame.setPlaytimeForever(1L);
        return steamGame;
    }
}
