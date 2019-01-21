package com.impurity.twitchwebintegrator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.impurity.twitchwebintegrator.model.TwitchUser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class TwitchService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String getUserURL = "https://api.twitch.tv/helix/users";
    private final String clientID = "Client-ID";
    private final String loginParam = "login";

    private final String dataKey = "data";
    private final String idKey = "id";
    private final String loginKey = "login";
    private final String displayNameKey = "display_name";
    private final String typeKey = "type";
    private final String broadcasterTypeKey = "broadcaster_type";
    private final String descriptionKey = "description";
    private final String profileImageUrlKey = "profile_image_url";
    private final String offlineImageUrlKey = "offline_image_url";
    private final String viewCountKey = "view_count";

    @Value("${client-id}")
    private String getClientID;

    public TwitchUser getUser(@RequestParam String channel) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getUserURL)
                .queryParam(loginParam, channel); // Put in properties / inject

        HttpHeaders headers = new HttpHeaders();
        headers.set(clientID, getClientID);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET,
                entity, String.class);

        if (response == null) throw new IllegalArgumentException("Twitch Response was Null");
        String responseBody = response.getBody();
        if (responseBody == null) throw new IllegalArgumentException("Twitch Response Body was Null");

        // Parse down to the data array
        JSONObject dataNode;
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
            JSONArray jsonArray = (JSONArray) jsonObject.get(dataKey);
            dataNode = (JSONObject) jsonArray.get(0);
        } catch (Exception e) {
            // TODO: Log Error
            throw new IllegalArgumentException("Twitch Response Body was Null");
        }

        // Create Twitch User
        TwitchUser twitchUser;
        try {
            twitchUser = new TwitchUser();
            twitchUser.setId((String) dataNode.get(idKey));
            twitchUser.setLogin((String) dataNode.get(loginKey));
            twitchUser.setDisplay_name((String) dataNode.get(displayNameKey));
            twitchUser.setType((String) dataNode.get(typeKey));
            twitchUser.setBroadcaster_type((String) dataNode.get(broadcasterTypeKey));
            twitchUser.setDescription((String) dataNode.get(descriptionKey));
            twitchUser.setProfile_image_url((String) dataNode.get(profileImageUrlKey));
            twitchUser.setOffline_image_url((String) dataNode.get(offlineImageUrlKey));
            twitchUser.setView_count((Long) dataNode.get(viewCountKey));
        } catch (Exception e) {
            // TODO: Log Error
            throw new IllegalArgumentException("Cannot create twitch user");
        }

        return twitchUser;
    }
}
