package com.impurity.twitchwebintegrator.test.utils;

import com.impurity.twitchwebintegrator.domain.twitch.TwitchFollower;
import com.impurity.twitchwebintegrator.domain.twitch.TwitchUser;

/**
 * @author tmk2003
 */
public class TwitchFactory {
    public static TwitchFollower[] getValidTwitchFollowerArray(int amount) {
        TwitchFollower[] twitchFollowers = new TwitchFollower[amount];
        for(int i = 0; i < amount; i++) {
            twitchFollowers[i] = getValidTwitchFollower();
        }
        return twitchFollowers;
    }

    public static TwitchFollower getValidTwitchFollower() {
        TwitchFollower twitchFollower = new TwitchFollower();
        twitchFollower.setFromId("fromid");
        twitchFollower.setFromName("fromname");
        twitchFollower.setToId("toid");
        twitchFollower.setToName("toname");
        twitchFollower.setFollowedAt("followedat");
        return twitchFollower;
    }

    public static TwitchUser getValidTwitchUser() {
        TwitchUser twitchUser = new TwitchUser();
        twitchUser.setOfflineImageUrl("offlineimageurl");
        twitchUser.setProfileImageUrl("profileimageurl");
        twitchUser.setBroadcasterType("broadcastertype");
        twitchUser.setDisplayName("displayname");
        twitchUser.setViewCount(123L);
        twitchUser.setDescription("description");
        twitchUser.setId("id");
        twitchUser.setLogin("login");
        twitchUser.setType("type");
        return twitchUser;
    }
}
