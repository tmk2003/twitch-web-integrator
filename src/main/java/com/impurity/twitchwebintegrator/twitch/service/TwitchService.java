package com.impurity.twitchwebintegrator.twitch.service;

import com.impurity.twitchwebintegrator.twitch.domain.TwitchFollower;
import com.impurity.twitchwebintegrator.twitch.domain.TwitchStream;
import com.impurity.twitchwebintegrator.twitch.domain.TwitchUser;

/**
 * @author Tyler Kokoszka
 */
public interface TwitchService {

    /**
     * Get the twitch user
     *
     * @param channel - Channel to grab the user for
     * @return A twitch user
     */
    TwitchUser getUser(String channel);

    /**
     * Get the twitch stream
     *
     * @param channel - Channel to grab the user for
     * @return A twitch stream
     */
    TwitchStream getStream(String channel);

    /**
     * Get the users recent followers
     *
     * @param channel - Channel to grab the followers for
     * @return An array of followers
     */
    Long getTotalFollowers(String channel);

    /**
     * Get the users recent followers
     *
     * @param channel - Channel to grab the followers for
     * @return An array of followers
     */
    TwitchFollower[] getRecentFollowers(String channel);
}
