package com.impurity.twitchwebintegrator.service.impl;

import com.impurity.twitchwebintegrator.client.SteamClient;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchFollowerCreationException;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchUserCreationException;
import com.impurity.twitchwebintegrator.model.SteamGame;
import com.impurity.twitchwebintegrator.service.SteamService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.impurity.twitchwebintegrator.constant.SteamKeys.*;

/**
 * @author tmk2003
 */
@Slf4j
@Service
public class SteamServiceImpl implements SteamService {

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
            log.error("Could not parse response from twitch", e);
            throw new TwitchUserCreationException("Twitch Response Body was Invalid", e);
        } catch (Exception e) {
            log.error("Error parsing out the data field", e);
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
                steamGame.setImgIconUrl(
                        _steamClient.imageHashToUrl(
                                steamGame.getAppId(),
                                (String) currentNode.get(IMG_ICON_URL))
                );
                steamGame.setImgLogoUrl(
                        _steamClient.imageHashToUrl(
                                steamGame.getAppId(),
                                (String) currentNode.get(IMG_LOGO_URL))
                );
                steamGame.setName((String) currentNode.get(NAME));
                steamGame.setPlaytimeForever((Long) currentNode.get(PLAYTIME_FOREVER));
                steamGames[i] = steamGame;
            } catch (Exception e) {
                log.error("Error constructing our twitch follower", e);
                throw new TwitchFollowerCreationException("Cannot create twitch follower", e);
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
