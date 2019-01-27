package com.impurity.twitchwebintegrator.service;

import com.impurity.twitchwebintegrator.model.SteamGame;

public interface SteamService {

    /**
     * Get the steam users library
     *
     * @param steamProfileID - ID to grab the user library of
     * @return A steam users library
     */
    SteamGame[] getGameLibrary(String steamProfileID);

    /**
     * Get the steam users library amount
     *
     * @param steamProfileID - ID to grab the user library of
     * @return A steam users library amount
     */
    Integer getGameLibraryAmount(String steamProfileID);
}
