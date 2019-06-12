package com.impurity.twitchwebintegrator.test.utils;

import com.impurity.twitchwebintegrator.model.SteamGame;
import com.impurity.twitchwebintegrator.model.TwitchFollower;
import com.impurity.twitchwebintegrator.model.TwitchUser;

/**
 * @author tmk2003
 */
public class SteamFactory {
    public static SteamGame[] getValidSteamGameArray(int amount) {
        SteamGame[] steamGames = new SteamGame[amount];
        for(int i = 0; i < amount; i++) {
            steamGames[i] = getValidSteamGame();
        }
        return steamGames;
    }

    public static SteamGame getValidSteamGame() {
        SteamGame steamGame = new SteamGame();
        steamGame.setAppId(123L);
        steamGame.setHasCommunityVisibleStats(true);
        steamGame.setImgIconUrl("");
        steamGame.setImgLogoUrl("");
        steamGame.setName("");
        steamGame.setPlaytimeForever(1L);
        return steamGame;
    }
}
