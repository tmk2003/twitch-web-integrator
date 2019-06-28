package com.impurity.twitchwebintegrator.osrs.builder;

import com.impurity.twitchwebintegrator.osrs.domain.OsrsScroll;
import com.impurity.twitchwebintegrator.osrs.domain.OsrsScrolls;
import com.impurity.twitchwebintegrator.osrs.utils.enums.OsrsHiScore;
import lombok.NonNull;

/**
 * @author tmk2003
 */
public class OsrsScrollsBuilder {
    private static final String DELIMITER = ",";
    private static final int SCROLLS_TOTAL = 6;
    private static final int SCROLL_VALUE_TOTAL = 2;

    private OsrsScrollsBuilder() {}

    /**
     * Create all scrolls based off hiscores
     *
     * @param hiscores the collection of values still delimited by commas
     * @return The osrs scrolls with proper mapping
     */
    public static OsrsScrolls buildScrolls(@NonNull final String[] hiscores) {
        if (hiscores.length != OsrsPlayerBuilder.HISCORE_TOTAL) {
            throw new IllegalArgumentException("Invalid hiscores length");
        }
        OsrsScroll[] scrolls = buildScrollsFromStringArray(
                new String[] {
                        hiscores[OsrsHiScore.CLUE_SCROLLS.ordinal()],
                        hiscores[OsrsHiScore.EASY_CLUE_SCROLLS.ordinal()],
                        hiscores[OsrsHiScore.MEDIUM_CLUE_SCROLLS.ordinal()],
                        hiscores[OsrsHiScore.HARD_CLUE_SCROLL.ordinal()],
                        hiscores[OsrsHiScore.ELITE_CLUE_SCROLL.ordinal()],
                        hiscores[OsrsHiScore.MASTER_CLUE_SCROLL.ordinal()],
                }
        );
        int i = -1;
        OsrsScrolls osrsScrolls = new OsrsScrolls();
        osrsScrolls.setClueScrolls(scrolls[++i]);
        osrsScrolls.setEasyClueScrolls(scrolls[++i]);
        osrsScrolls.setMediumClueScrolls(scrolls[++i]);
        osrsScrolls.setHardClueScrolls(scrolls[++i]);
        osrsScrolls.setEliteClueScrolls(scrolls[++i]);
        osrsScrolls.setMasterClueScrolls(scrolls[++i]);
        return osrsScrolls;
    }

    /**
     * Create all scroll based off 6 scrolls
     *
     * @param scrollValues the collection of values still delimited by commas
     * @return The collection of scrolls
     */
    private static OsrsScroll[] buildScrollsFromStringArray(final String[] scrollValues) {
        OsrsScroll[] scrolls = new OsrsScroll[SCROLLS_TOTAL];
        for (int i = 0; i < scrolls.length; i++) {
            scrolls[i] = buildScrollFromString(scrollValues[i]);
        }
        return scrolls;
    }
    /**
     * Create a scroll based off 2 longs
     *
     * @param scrollValue - Scroll string with comma delimiter
     * @return The Osrs scrolls object that was created based off the parameter
     */
    private static OsrsScroll buildScrollFromString(final String scrollValue) {
        String[] skillValues = scrollValue.split(DELIMITER);
        if (skillValues.length != SCROLL_VALUE_TOTAL) {
            throw new IllegalArgumentException("Invalid skillValue length");
        }
        OsrsScroll scroll = new OsrsScroll();
        scroll.setRank(Long.parseLong(skillValues[0]));
        scroll.setScore(Long.parseLong(skillValues[1]));
        return scroll;
    }
}
