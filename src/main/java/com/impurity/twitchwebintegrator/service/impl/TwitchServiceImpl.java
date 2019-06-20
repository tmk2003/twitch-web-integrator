package com.impurity.twitchwebintegrator.service.impl;

import com.impurity.twitchwebintegrator.client.TwitchClient;
import com.impurity.twitchwebintegrator.client.response.TwitchApiFollowerResponse;
import com.impurity.twitchwebintegrator.client.response.TwitchApiStreamResponse;
import com.impurity.twitchwebintegrator.client.response.TwitchApiUserResponse;
import com.impurity.twitchwebintegrator.domain.TwitchFollower;
import com.impurity.twitchwebintegrator.domain.TwitchStream;
import com.impurity.twitchwebintegrator.domain.TwitchUser;
import com.impurity.twitchwebintegrator.service.TwitchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * @author tmk2003
 */
@Slf4j
@Service
public class TwitchServiceImpl implements TwitchService {

    @Autowired
    private TwitchClient twitchClient;

    /**
     * Get the twitch user
     *
     * @param channel - Channel to grab the user for
     * @return A twitch user
     */
    @Override
    public TwitchUser getUser(@NotBlank String channel) {
        ResponseEntity<TwitchApiUserResponse> responseEntity = twitchClient.getUser(channel);

        TwitchApiUserResponse twitchApiUserResponse = Optional
                .ofNullable(responseEntity.getBody())
                .orElseThrow(() -> {
                    log.error("There was no response entity found while getting twitch user");
                    return new RuntimeException("No user response body found");
                });

        TwitchUser[] twitchUsers = Optional
                .ofNullable(twitchApiUserResponse.getUsers())
                .orElseThrow(() -> {
                    log.error("The user array was not instantiated");
                    return new RuntimeException("User array not instantiated");
                });

        if (twitchUsers.length == 0) {
            log.error("There were no users found while getting twitch user");
            throw new RuntimeException("No users found");
        }

        return Optional
                .ofNullable(twitchUsers[0])
                .orElseThrow(() -> {
                    log.error("The first user found was not instantiated");
                    return new RuntimeException("The first user found was not instantiated");
                });
    }


    /**
     * Get the twitch user
     *
     * @param channel - Channel to grab the user for
     * @return A twitch user
     */
    @Override
    public TwitchStream getStream(@NotBlank String channel) {
        ResponseEntity<TwitchApiStreamResponse> responseEntity = twitchClient.getStream(channel);

        TwitchApiStreamResponse twitchApiStreamResponse = Optional
                .ofNullable(responseEntity.getBody())
                .orElseThrow(() -> {
                    log.error("There was no response entity found while getting twitch stream");
                    return new RuntimeException("No stream response body found");
                });

        TwitchStream[] twitchStreams = Optional
                .ofNullable(twitchApiStreamResponse.getStreams())
                .orElseThrow(() -> {
                    log.error("The stream array was not instantiated");
                    return new RuntimeException("Stream array not instantiated");
                });

        if (twitchStreams.length == 0) {
            log.error("There were no streams found while getting twitch user");
            throw new RuntimeException("No streams found");
        }

        return Optional
                .ofNullable(twitchStreams[0])
                .orElseThrow(() -> {
                    log.error("The first stream found was not instantiated");
                    return new RuntimeException("The first stream found was not instantiated");
                });
    }

    /**
     * Get the users recent followers
     *
     * @param channel - Channel to grab the followers for
     * @return An array of followers
     */
    @Override
    public Long getTotalFollowers(@NotBlank String channel) {
        String userId = this.getUser(channel).getId();
        ResponseEntity<TwitchApiFollowerResponse> responseEntity = twitchClient.getFollowers(userId);

        TwitchApiFollowerResponse twitchApiFollowerResponse = Optional
                .ofNullable(responseEntity.getBody())
                .orElseThrow(() -> new RuntimeException("No total followers response body found"));

        return Optional
                .ofNullable(twitchApiFollowerResponse.getTotal())
                .orElseThrow(() -> new RuntimeException("No total followers found"));
    }

    /**
     * Get the users recent followers
     *
     * @param channel - Channel to grab the followers for
     * @return An array of followers
     */
    @Override
    public TwitchFollower[] getRecentFollowers(@NotBlank String channel) {
        String userId = this.getUser(channel).getId();
        ResponseEntity<TwitchApiFollowerResponse> responseEntity = twitchClient.getFollowers(userId);

        TwitchApiFollowerResponse twitchApiFollowerResponse = Optional
                .ofNullable(responseEntity.getBody())
                .orElseThrow(() -> new RuntimeException("No recent followers response body found"));

        return Optional
                .ofNullable(twitchApiFollowerResponse.getFollowers())
                .orElseThrow(() -> new RuntimeException("No recent followers found"));
    }
}
