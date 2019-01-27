package com.impurity.twitchwebintegrator.service.impl;

import com.impurity.twitchwebintegrator.client.SteamClient;
import com.impurity.twitchwebintegrator.exception.TwitchFollowerException;
import com.impurity.twitchwebintegrator.exception.TwitchUserException;
import com.impurity.twitchwebintegrator.model.SteamGame;
import com.impurity.twitchwebintegrator.model.TwitchFollower;
import com.impurity.twitchwebintegrator.service.SteamService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.impurity.twitchwebintegrator.constant.SteamKeys.*;
import static com.impurity.twitchwebintegrator.constant.TwitchKeys.*;

@Service
public class SteamServiceImpl implements SteamService {

    private final Logger LOGGER = LoggerFactory.getLogger(SteamServiceImpl.class);

    @Autowired
    private SteamClient _steamClient;

    @Override
    public SteamGame[] getGameLibrary(String steamProfileID) {
        String responseBody = _steamClient.sendGetLibrary(steamProfileID);

        // Parse down to the data array
        JSONArray gamesNode;
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
            JSONObject JSONObjectResponse = (JSONObject) jsonObject.get(RESPONSE);
            gamesNode = (JSONArray) JSONObjectResponse.get(GAMES);
        } catch (ParseException e) {
            LOGGER.error("Could not parse response from twitch", e);
            throw new TwitchUserException("Twitch Response Body was Invalid", e);
        } catch (Exception e) {
            LOGGER.error("Error parsing out the data field", e);
            throw new IllegalArgumentException("Twitch Response Body was Invalid", e);
        }

        SteamGame[] steamGames = new SteamGame[gamesNode.size()];
        for(int i = 0; i < gamesNode.size(); i++) {
            try {
                // Grab current node index
                JSONObject currentNode = (JSONObject) gamesNode.get(i);

                // Create Twitch User
                SteamGame steamGame = new SteamGame();
                steamGame.setAppId((Long) currentNode.get(APPID));
                steamGame.setHasCommunityVisibleStats((Boolean) currentNode.get(HAS_COMMUNITY_VISIBLE_STATS));
                steamGame.setImgIconUrl((String) currentNode.get(IMG_ICON_URL));
                steamGame.setImgLogoUrl((String) currentNode.get(IMG_LOGO_URL));
                steamGame.setName((String) currentNode.get(NAME));
                steamGame.setPlaytimeForever((Long) currentNode.get(PLAYTIME_FOREVER));
                steamGames[i] = steamGame;
            } catch (Exception e) {
                LOGGER.error("Error constructing our twitch follower", e);
                throw new TwitchFollowerException("Cannot create twitch follower", e);
            }
        }

        return steamGames;
    }

    @Override
    public Integer getGameLibraryAmount(String steamProfileID) {
        SteamGame[] steamGames = getGameLibrary(steamProfileID);
        return steamGames.length;
    }

}
