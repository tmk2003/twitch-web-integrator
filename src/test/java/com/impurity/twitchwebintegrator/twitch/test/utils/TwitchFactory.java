package com.impurity.twitchwebintegrator.twitch.test.utils;

import com.impurity.twitchwebintegrator.twitch.domain.TwitchFollower;
import com.impurity.twitchwebintegrator.twitch.domain.TwitchStream;
import com.impurity.twitchwebintegrator.twitch.domain.TwitchUser;

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

    public static TwitchStream getValidTwitchStream() {
        TwitchStream twitchStream = new TwitchStream();
        twitchStream.setCommunityIds(new String[]{"communityids"});
        twitchStream.setGameId("gameid");
        twitchStream.setId("id");
        twitchStream.setLanguage("language");
        twitchStream.setStartedAt("startedat");
        twitchStream.setTagIds(new String[]{"tagids"});
        twitchStream.setThumbnailUrl("thumbnailurl");
        twitchStream.setTitle("title");
        twitchStream.setType("type");
        twitchStream.setUserId("userid");
        twitchStream.setUserName("username");
        twitchStream.setViewerCount(123L);
        return twitchStream;
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
