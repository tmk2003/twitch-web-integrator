package com.impurity.twitchwebintegrator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.impurity.twitchwebintegrator.client.TwitchClient;
import com.impurity.twitchwebintegrator.exception.TwitchFollowerException;
import com.impurity.twitchwebintegrator.exception.TwitchUserException;
import com.impurity.twitchwebintegrator.model.TwitchFollower;
import com.impurity.twitchwebintegrator.model.TwitchUser;
import com.impurity.twitchwebintegrator.properties.TwitchProperties;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import static com.impurity.twitchwebintegrator.constant.TwitchKeys.*;


@Service
public class TwitchService {

    @Autowired
    private TwitchClient _twitchClient;
    @Autowired
    private TwitchProperties _twitchProperties;

    private final Logger LOGGER = LoggerFactory.getLogger(TwitchService.class);
    private final String clientID = "Client-ID";
    private final String loginParam = "login";

    @Value("${client-id}")
    private String getClientID;

    /**
     * Get the twitch user
     * @param channel - Channel to grab the user for
     * @return A twitch user
     */
    public TwitchUser getUser(@RequestParam String channel) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(_twitchProperties.getGetUserUrl())
                .queryParam(loginParam, channel);

        // Attempt to contact twitch API with our constructed url
        String responseBody = _twitchClient.sendTwitchRequest(builder.toUriString());

        // Parse down to the data array
        JSONObject dataNode;
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
            JSONArray jsonArray = (JSONArray) jsonObject.get(DATA_KEY);
            dataNode = (JSONObject) jsonArray.get(0);
        } catch (ParseException e) {
            LOGGER.error("Could not parse response from twitch", e);
            throw new TwitchUserException("Twitch Response Body was Invalid", e);
        } catch (Exception e) {
            LOGGER.error("Error parsing out the data field", e);
            throw new IllegalArgumentException("Twitch Response Body was Invalid", e);
        }

        // Create Twitch User
        TwitchUser twitchUser;
        try {
            twitchUser = new TwitchUser();
            twitchUser.setId((String) dataNode.get(ID_KEY));
            twitchUser.setLogin((String) dataNode.get(LOGIN_KEY));
            twitchUser.setDisplay_name((String) dataNode.get(DISPLAY_NAME_KEY));
            twitchUser.setType((String) dataNode.get(TYPE_KEY));
            twitchUser.setBroadcaster_type((String) dataNode.get(BROADCASTER_TYPE_KEY));
            twitchUser.setDescription((String) dataNode.get(DESCIPTION_KEY));
            twitchUser.setProfile_image_url((String) dataNode.get(PROFILE_IMAGE_URL_KEY));
            twitchUser.setOffline_image_url((String) dataNode.get(OFFLINE_IMAGE_URL_KEY));
            twitchUser.setView_count((Long) dataNode.get(VIEW_COUNT_KEY));
        } catch (Exception e) {
            LOGGER.error("Error constructing our twitch user", e);
            throw new TwitchUserException("Cannot create twitch user", e);
        }

        return twitchUser;
    }

    /**
     * Get the users recent followers
     * @param channel - Channel to grab the followers for
     * @return An array of followers
     */
    public Long getTotalFollowers(@RequestParam String channel) {
        TwitchUser twitchUser = this.getUser(channel);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(_twitchProperties.getGetFollowersUrl())
                .queryParam(TO_ID_KEY, twitchUser.getId());

        // Attempt to contact twitch api
        String responseBody = _twitchClient.sendTwitchRequest(builder.toUriString());

        // Parse down to the data array
        Long totalFollowers;
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
            totalFollowers = (Long) jsonObject.get(TOTAL_KEY);
        } catch (ParseException e) {
            LOGGER.error("Could not parse response from twitch", e);
            throw new TwitchFollowerException("Twitch Response Body was Invalid", e);
        } catch (Exception e) {
            LOGGER.error("Error parsing out the data field", e);
            throw new IllegalArgumentException("Twitch Response Body was Invalid", e);
        }

        return totalFollowers;
    }

    /**
     * Get the users recent followers
     * @param channel - Channel to grab the followers for
     * @return An array of followers
     */
    public TwitchFollower[] getRecentFollowers(@RequestParam String channel) {
        TwitchUser twitchUser = this.getUser(channel);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(_twitchProperties.getGetFollowersUrl())
                .queryParam(TO_ID_KEY, twitchUser.getId());

        // Attempt to contact twitch api
        String responseBody = _twitchClient.sendTwitchRequest(builder.toUriString());

        // Parse down to the data array
        JSONArray jsonArray;
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
            jsonArray = (JSONArray) jsonObject.get(DATA_KEY);
        } catch (ParseException e) {
            LOGGER.error("Could not parse response from twitch", e);
            throw new TwitchFollowerException("Twitch Response Body was Invalid", e);
        } catch (Exception e) {
            LOGGER.error("Error parsing out the data field", e);
            throw new IllegalArgumentException("Twitch Response Body was Invalid", e);
        }

        TwitchFollower[] twitchFollowers = new TwitchFollower[jsonArray.size()];
        for(int i = 0; i < jsonArray.size(); i++) {
            try {
                // Grab current node index
                JSONObject currentNode = (JSONObject) jsonArray.get(i);

                // Create Twitch User
                TwitchFollower twitchFollower = new TwitchFollower();
                twitchFollower.setFrom_id((String) currentNode.get(FROM_ID_KEY));
                twitchFollower.setFrom_name((String) currentNode.get(FROM_NAME_KEY));
                twitchFollower.setTo_id((String) currentNode.get(TO_ID_KEY));
                twitchFollower.setTo_name((String) currentNode.get(TO_NAME_KEY));
                twitchFollower.setFollowed_at((String) currentNode.get(FOLLOWED_AT_KEY));
                twitchFollowers[i] = twitchFollower;
            } catch (Exception e) {
                LOGGER.error("Error constructing our twitch follower", e);
                throw new TwitchFollowerException("Cannot create twitch follower", e);
            }
        }

        return twitchFollowers;
    }
}
