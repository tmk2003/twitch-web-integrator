package com.impurity.twitchwebintegrator.service;

import com.impurity.twitchwebintegrator.domain.osrs.OsrsPlayer;

/**
 * @author Tyler Kokoszka
 */
public interface OsrsService {

    OsrsPlayer getPlayer(String playerName);

    String getItem(Long item);
}
