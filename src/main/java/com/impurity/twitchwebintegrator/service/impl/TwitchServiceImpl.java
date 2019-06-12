package com.impurity.twitchwebintegrator.service.impl;

import com.impurity.twitchwebintegrator.client.TwitchClient;
import com.impurity.twitchwebintegrator.exception.twitch.*;
import com.impurity.twitchwebintegrator.model.TwitchFollower;
import com.impurity.twitchwebintegrator.model.TwitchStream;
import com.impurity.twitchwebintegrator.model.TwitchUser;
import com.impurity.twitchwebintegrator.properties.TwitchProperties;
import com.impurity.twitchwebintegrator.service.TwitchService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.impurity.twitchwebintegrator.constant.TwitchKeys.*;

/**
 * @author tmk2003
 */
@Slf4j
@Service
public class TwitchServiceImpl implements TwitchService {

    @Autowired
    private TwitchClient _twitchClient;
    @Autowired
    private TwitchProperties _twitchProperties;

    /**
     * Get the twitch user
     *
     * @param channel - Channel to grab the user for
     * @return A twitch user
     */
    @Override
    public TwitchUser getUser(String channel) {
        String responseBody = _twitchClient.sendGetUser(channel);

        JSONArray jsonArray;
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
            jsonArray = (JSONArray) jsonObject.get(DATA_KEY);
        } catch (ParseException e) {
            log.error("Could not parse response from twitch", e);
            throw new TwitchUserCreationException("Twitch Response Body was Invalid", e);
        } catch (Exception e) {
            log.error("Error parsing out the data field", e);
            throw new IllegalArgumentException("Twitch Response Body was Invalid", e);
        }
        if(jsonArray.isEmpty()) {
            throw new TwitchUserNotFoundException(channel + " was not found in twitch API");
        }

        JSONObject dataNode = (JSONObject) jsonArray.get(0);

        TwitchUser twitchUser;
        try {
            twitchUser = new TwitchUser();
            twitchUser.setId((String) dataNode.get(ID_KEY));
            twitchUser.setLogin((String) dataNode.get(LOGIN_KEY));
            twitchUser.setDisplayName((String) dataNode.get(DISPLAY_NAME_KEY));
            twitchUser.setType((String) dataNode.get(TYPE_KEY));
            twitchUser.setBroadcasterType((String) dataNode.get(BROADCASTER_TYPE_KEY));
            twitchUser.setDescription((String) dataNode.get(DESCRIPTION_KEY));
            twitchUser.setProfileImageUrl((String) dataNode.get(PROFILE_IMAGE_URL_KEY));
            twitchUser.setOfflineImageUrl((String) dataNode.get(OFFLINE_IMAGE_URL_KEY));
            twitchUser.setViewCount((Long) dataNode.get(VIEW_COUNT_KEY));
        } catch (Exception e) {
            log.error("Error constructing our twitch user", e);
            throw new TwitchUserCreationException("Cannot create twitch user", e);
        }

        return twitchUser;
    }


    /**
     * Get the twitch user
     *
     * @param channel - Channel to grab the user for
     * @return A twitch user
     */
    @Override
    public TwitchStream getStream(String channel) {
        String responseBody = _twitchClient.sendGetStream(channel);

        JSONObject jsonObject;
        try {
            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(responseBody);
        } catch (ParseException e) {
            log.error("Could not parse response from twitch", e);
            throw new TwitchUserCreationException("Twitch Response Body was Invalid", e);
        }

        JSONArray jsonArray = (JSONArray) jsonObject.get(DATA_KEY);
        if(jsonArray.isEmpty()) {
            throw new TwitchStreamNotFoundException("Twitch stream not found.");
        }

        JSONObject dataNode = (JSONObject) jsonArray.get(0);
        TwitchStream twitchStream;
        try {
            twitchStream = new TwitchStream();
            twitchStream.setId((String) dataNode.get(ID_KEY));
            twitchStream.setUserId((String) dataNode.get(USER_ID_KEY));
            twitchStream.setUserName((String) dataNode.get(USER_NAME_KEY));
            twitchStream.setGameId((String) dataNode.get(GAME_ID_KEY));
            twitchStream.setType((String) dataNode.get(TYPE_KEY));
            twitchStream.setTitle((String) dataNode.get(TITLE_KEY));
            twitchStream.setLanguage((String) dataNode.get(LANGUAGE_KEY));
            twitchStream.setThumbnailUrl((String) dataNode.get(THUMBNAIL_URL_KEY));
            twitchStream.setViewerCount((Long) dataNode.get(VIEWER_COUNT_KEY));
            twitchStream.setStartedAt((String) dataNode.get(STARTED_AT_KEY));
            twitchStream.setCommunityIds(jsonArrayToStringArray((JSONArray) dataNode.get(COMMUNITY_IDS_KEY)));
            twitchStream.setTagIds(jsonArrayToStringArray((JSONArray) dataNode.get(TAG_IDS_KEY)));
        } catch (Exception e) {
            log.error("Error constructing our twitch stream", e);
            throw new TwitchUserCreationException("Cannot create twitch stream", e);
        }

        return twitchStream;
    }

    /**
     * Get the users recent followers
     *
     * @param channel - Channel to grab the followers for
     * @return An array of followers
     */
    @Override
    public Long getTotalFollowers(String channel) {
        String responseBody = _twitchClient.sendGetFollowers(this.getUser(channel));

        JSONObject jsonObject;
        try {
            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(responseBody);
        } catch (ParseException e) {
            log.error("Could not parse response from twitch", e);
            throw new TwitchFollowerCreationException("Twitch Response Body was Invalid", e);
        }

        return (Long) jsonObject.get(TOTAL_KEY);
    }

    /**
     * Get the users recent followers
     *
     * @param channel - Channel to grab the followers for
     * @return An array of followers
     */
    @Override
    public TwitchFollower[] getRecentFollowers(String channel) {
        String responseBody = _twitchClient.sendGetFollowers(this.getUser(channel));

        JSONObject jsonObject;
        try {
            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(responseBody);
        } catch (ParseException e) {
            log.error("Could not parse response from twitch", e);
            throw new TwitchFollowerCreationException("Twitch Response Body was Invalid", e);
        }

        JSONArray jsonArray = (JSONArray) jsonObject.get(DATA_KEY);
        if(jsonArray.isEmpty()) {
            throw new TwitchFollowerNotFoundException("Twitch Followers not found");
        }

        TwitchFollower[] twitchFollowers = new TwitchFollower[jsonArray.size()];
        for(int i = 0; i < jsonArray.size(); i++) {
            try {
                JSONObject currentNode = (JSONObject) jsonArray.get(i);
                TwitchFollower twitchFollower = new TwitchFollower();
                twitchFollower.setFromId((String) currentNode.get(FROM_ID_KEY));
                twitchFollower.setFromName((String) currentNode.get(FROM_NAME_KEY));
                twitchFollower.setToId((String) currentNode.get(TO_ID_KEY));
                twitchFollower.setToName((String) currentNode.get(TO_NAME_KEY));
                twitchFollower.setFollowedAt((String) currentNode.get(FOLLOWED_AT_KEY));
                twitchFollowers[i] = twitchFollower;
            } catch (Exception e) {
                log.error("Error constructing our twitch follower", e);
                throw new TwitchFollowerCreationException("Cannot create twitch follower", e);
            }
        }

        return twitchFollowers;
    }

    /**
     * Converts a jsonArray to a stringArray
     * @param jsonArray JSON array from twitch
     * @return String array for a pojo to use
     */
    private String[] jsonArrayToStringArray(JSONArray jsonArray) {
        String[] stringArray = new String[jsonArray.size()];
        for(int i = 0; i < jsonArray.size(); i++) {
            stringArray[i] = (String) jsonArray.get(i);
        }
        return stringArray;
    }
}
