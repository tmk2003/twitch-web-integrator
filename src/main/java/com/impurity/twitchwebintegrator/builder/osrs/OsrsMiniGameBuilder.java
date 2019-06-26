package com.impurity.twitchwebintegrator.builder.osrs;

import com.impurity.twitchwebintegrator.domain.osrs.OsrsMiniGame;
import com.impurity.twitchwebintegrator.domain.osrs.OsrsMiniGames;
import com.impurity.twitchwebintegrator.domain.osrs.OsrsScroll;
import com.impurity.twitchwebintegrator.domain.osrs.OsrsScrolls;
import com.impurity.twitchwebintegrator.utils.enums.OsrsHiScore;
import lombok.NonNull;

/**
 * @author tmk2003
 */
public class OsrsMiniGameBuilder {
    private static final String DELIMITER = ",";
    private static final int MINIGAMES_TOTAL = 3;
    private static final int MINIGAME_VALUE_TOTAL = 2;

    private OsrsMiniGameBuilder() {}

    /**
     * Create all scrolls based off hiscores
     *
     * @param hiscores the collection of values still delimited by commas
     * @return The osrs scrolls with proper mapping
     */
    public static OsrsMiniGames buildMiniGames(@NonNull final String[] hiscores) {
        OsrsMiniGame[] miniGames = buildMiniGamesFromStringArray(
                new String[] {
                        hiscores[OsrsHiScore.BOUNTY_HUNTER.ordinal()],
                        hiscores[OsrsHiScore.BOUNTY_HUNTER_ROGUES.ordinal()],
                        hiscores[OsrsHiScore.LAST_MAN_STANDING.ordinal()],
                }
        );
        int i = -1;
        OsrsMiniGames osrsMiniGames = new OsrsMiniGames();
        osrsMiniGames.setBountyHunter(miniGames[++i]);
        osrsMiniGames.setBountyHunterRogues(miniGames[++i]);
        osrsMiniGames.setLastManStanding(miniGames[++i]);
        return osrsMiniGames;
    }

    /**
     * Create all minigame based off 6 scrolls
     *
     * @param miniGameValues the collection of values still delimited by commas
     * @return The collection of minigames
     */
    private static OsrsMiniGame[] buildMiniGamesFromStringArray(final String[] miniGameValues) {
        if (miniGameValues.length != MINIGAMES_TOTAL) {
            throw new IllegalArgumentException("Invalid miniGameValue length");
        }
        OsrsMiniGame[] miniGames = new OsrsMiniGame[MINIGAMES_TOTAL];
        for (int i = 0; i < miniGames.length; i++) {
            miniGames[i] = buildMiniGame(miniGameValues[i]);
        }
        return miniGames;
    }

    /**
     * Create a minigame based off 2 longs
     *
     * @param miniGameValue - MiniGame string with comma delimiter
     * @return The Osrs minigame object that was created based off the parameter
     */
    private static OsrsMiniGame buildMiniGame(final String miniGameValue) {
        String[] miniGameValues = miniGameValue.split(DELIMITER);
        if (miniGameValues.length != MINIGAME_VALUE_TOTAL) {
            throw new IllegalArgumentException("Invalid miniGameValue length");
        }
        OsrsMiniGame miniGame = new OsrsMiniGame();
        miniGame.setRank(Long.parseLong(miniGameValues[0]));
        miniGame.setScore(Long.parseLong(miniGameValues[1]));
        return miniGame;
    }
}
