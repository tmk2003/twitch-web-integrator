package com.impurity.twitchwebintegrator.builder.osrs;

import com.impurity.twitchwebintegrator.domain.osrs.OsrsPlayer;
import lombok.NonNull;

import static com.impurity.twitchwebintegrator.builder.osrs.OsrsMiniGameBuilder.buildMiniGames;
import static com.impurity.twitchwebintegrator.builder.osrs.OsrsScrollsBuilder.buildScrolls;
import static com.impurity.twitchwebintegrator.builder.osrs.OsrsSkillsBuilder.buildSkills;

/**
 * @author tmk2003
 */
public class OsrsPlayerBuilder {
    private static final String DELIMITER = "\\n";
    private static final int HISCORE_TOTAL = 34;

    private OsrsPlayerBuilder() {}

    public static OsrsPlayer buildPlayer(
            @NonNull final String name,
            @NonNull final String type,
            @NonNull final String playerHiScores
    ) {
        String[] scores = buildScores(playerHiScores);
        OsrsPlayer player = new OsrsPlayer();
        player.setName(name);
        player.setType(type);
        player.setSkills(buildSkills(scores));
        player.setScrolls(buildScrolls(scores));
        player.setMiniGames(buildMiniGames(scores));
        return player;
    }

    /**
     * Create all the scores based off a string with newline delimiters
     *
     * @param playerHiScores Hiscores with newline delimiters
     * @return A collection of hiscores still with comma delimiter
     */
    private static String[] buildScores(final String playerHiScores) {
        String[] scores = playerHiScores.split(DELIMITER);
        if (scores.length != HISCORE_TOTAL) {
            throw new IllegalArgumentException("Invalid playerHiScores length");
        }
        return scores;
    }
}
