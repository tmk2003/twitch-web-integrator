package com.impurity.twitchwebintegrator.steam.service;

import com.impurity.twitchwebintegrator.steam.domain.SteamLibrary;

/**
 * @author tmk2003
 */
public interface SteamService {

    /**
     * Get the steam users library
     *
     * @param steamProfileID - ID to grab the user library of
     * @return A steam users library
     */
    SteamLibrary getGameLibrary(String steamProfileID);

    /**
     * Get the steam users library total
     *
     * @param steamProfileID - ID to grab the user library of
     * @return A steam users library total
     */
    Long getGameLibraryTotal(String steamProfileID);
}
