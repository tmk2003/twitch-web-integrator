package com.impurity.twitchwebintegrator.osrs.service;

import com.impurity.twitchwebintegrator.osrs.domain.OsrsItem;
import com.impurity.twitchwebintegrator.osrs.domain.OsrsPlayer;

/**
 * @author Tyler Kokoszka
 */
public interface OsrsService {

    OsrsPlayer getPlayer(String playerName);

    OsrsItem getItem(Long item);
}
