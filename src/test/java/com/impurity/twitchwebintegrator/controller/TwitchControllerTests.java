package com.impurity.twitchwebintegrator.controller;

import com.impurity.twitchwebintegrator.exception.twitch.*;
import com.impurity.twitchwebintegrator.model.TwitchFollower;
import com.impurity.twitchwebintegrator.model.TwitchStream;
import com.impurity.twitchwebintegrator.model.TwitchUser;
import com.impurity.twitchwebintegrator.service.TwitchService;
import com.impurity.twitchwebintegrator.test.utils.AbstractTest;
import com.impurity.twitchwebintegrator.test.utils.TwitchFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.impurity.twitchwebintegrator.constant.Profiles.UNIT_TEST;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author tmk2003
 */
@Tag("Controller")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles(UNIT_TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TwitchControllerTests extends AbstractTest {

    @MockBean
    private TwitchService _mockTwitchService;
    @Autowired
    private MockMvc _mockMvc;
    private final String MOCK_CHANNEL_NAME = "abc123";

    /******************* Get Twitch User *******************/
    @Test
    @DisplayName("When getting a twitch user and is found, return 200 and user")
    public void twitch_user_return_200() throws Exception {
        TwitchUser twitchUser = TwitchFactory.getValidTwitchUser();

        when(_mockTwitchService.getUser(MOCK_CHANNEL_NAME)).thenReturn(twitchUser);
        _mockMvc.perform(
                get("/twitch/" + MOCK_CHANNEL_NAME + "/user")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(
                        mapToJson(twitchUser)
                ));
    }

    @Test
    @DisplayName("When getting a twitch user and is not found, return 404")
    public void no_twitch_user_return_404() throws Exception {
        when(_mockTwitchService.getUser(MOCK_CHANNEL_NAME)).thenThrow(TwitchUserNotFoundException.class);
        _mockMvc.perform(
                get("/twitch/" + MOCK_CHANNEL_NAME + "/user")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("When getting a twitch user and it cannot be created, return 500")
    public void twitch_user_creation_failure_return_500() throws Exception {
        when(_mockTwitchService.getUser(MOCK_CHANNEL_NAME)).thenThrow(TwitchUserCreationException.class);
        _mockMvc.perform(
                get("/twitch/" + MOCK_CHANNEL_NAME + "/user")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isInternalServerError());
    }

    /******************* Get Twitch Stream *******************/
    @Test
    @DisplayName("When getting a twitch stream and is found, return 200 and stream")
    public void twitch_stream_return_200() throws Exception {
        TwitchStream twitchStream = new TwitchStream();

        when(_mockTwitchService.getStream(MOCK_CHANNEL_NAME)).thenReturn(twitchStream);
        _mockMvc.perform(
                get("/twitch/" + MOCK_CHANNEL_NAME + "/stream")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(
                        mapToJson(twitchStream)
                ));
    }

    @Test
    @DisplayName("When getting a twitch stream and it cannot be found, return 404")
    public void no_twitch_stream_return_404() throws Exception {
        when(_mockTwitchService.getStream(MOCK_CHANNEL_NAME)).thenThrow(TwitchStreamNotFoundException.class);
        _mockMvc.perform(
                get("/twitch/" + MOCK_CHANNEL_NAME + "/stream")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("When getting a twitch stream and it cannot be created, return 500")
    public void twitch_stream_creation_failure_return_500() throws Exception {
        when(_mockTwitchService.getStream(MOCK_CHANNEL_NAME)).thenThrow(TwitchStreamCreationException.class);
        _mockMvc.perform(
                get("/twitch/" + MOCK_CHANNEL_NAME + "/stream")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isInternalServerError());
    }

    /******************* Get Twitch Recent Followers *******************/
    @Test
    @DisplayName("When getting twitch recent followers and is found, return 200 and user")
    public void twitch_followers_recent_return_200() throws Exception {
        TwitchFollower[] twitchFollowers = TwitchFactory.getValidTwitchFollowerArray(10);
        when(_mockTwitchService.getRecentFollowers(MOCK_CHANNEL_NAME)).thenReturn(twitchFollowers);
        _mockMvc.perform(
                get("/twitch/" + MOCK_CHANNEL_NAME + "/followers/recent")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(
                        mapToJson(twitchFollowers)
                ));
    }

    @Test
    @DisplayName("When getting twitch recent followers and is not found, return 404")
    public void no_twitch_followers_recent_return_404() throws Exception {
        when(_mockTwitchService.getRecentFollowers(MOCK_CHANNEL_NAME)).thenThrow(TwitchFollowerNotFoundException.class);
        _mockMvc.perform(
                get("/twitch/" + MOCK_CHANNEL_NAME + "/followers/recent")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("When getting twitch recent followers and it cannot be created, return 500")
    public void twitch_followers_recent_creation_failure_return_500() throws Exception {
        when(_mockTwitchService.getRecentFollowers(MOCK_CHANNEL_NAME)).thenThrow(TwitchFollowerCreationException.class);
        _mockMvc.perform(
                get("/twitch/" + MOCK_CHANNEL_NAME + "/followers/recent")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isInternalServerError());
    }

    /******************* Get Twitch Total Followers *******************/
    @Test
    @DisplayName("When getting twitch total followers and is found, return 200 and user")
    public void twitch_followers_total_return_200() throws Exception {
        when(_mockTwitchService.getTotalFollowers(MOCK_CHANNEL_NAME)).thenReturn(6L);
        _mockMvc.perform(
                get("/twitch/" + MOCK_CHANNEL_NAME + "/followers/total")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json("6"));
    }

    @Test
    @DisplayName("When getting twitch total followers and is not found, return 404")
    public void no_twitch_followers_total_return_404() throws Exception {
        when(_mockTwitchService.getTotalFollowers(MOCK_CHANNEL_NAME)).thenThrow(TwitchFollowerNotFoundException.class);
        _mockMvc.perform(
                get("/twitch/" + MOCK_CHANNEL_NAME + "/followers/total")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("When getting twitch total followers and it cannot be created, return 500")
    public void twitch_followers_total_creation_failure_return_500() throws Exception {
        when(_mockTwitchService.getTotalFollowers(MOCK_CHANNEL_NAME)).thenThrow(TwitchFollowerCreationException.class);
        _mockMvc.perform(
                get("/twitch/" + MOCK_CHANNEL_NAME + "/followers/total")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isInternalServerError());
    }
}
