package com.impurity.twitchwebintegrator.service.impl;

import com.impurity.twitchwebintegrator.client.TwitchClient;
import com.impurity.twitchwebintegrator.client.response.TwitchApiFollowerResponse;
import com.impurity.twitchwebintegrator.client.response.TwitchApiStreamResponse;
import com.impurity.twitchwebintegrator.client.response.TwitchApiUserResponse;
import com.impurity.twitchwebintegrator.domain.twitch.TwitchFollower;
import com.impurity.twitchwebintegrator.domain.twitch.TwitchStream;
import com.impurity.twitchwebintegrator.domain.twitch.TwitchUser;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchFollowersNotFoundException;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchStreamNotFoundException;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchUserNotFoundException;
import com.impurity.twitchwebintegrator.test.utils.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.impurity.twitchwebintegrator.constant.Profiles.UNIT_TEST;
import static com.impurity.twitchwebintegrator.test.utils.TwitchFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author tmk2003
 */
@Tag("Service")
@ExtendWith(SpringExtension.class)
@ActiveProfiles(UNIT_TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TwitchServiceImplTests extends AbstractTest {

    @Mock
    private TwitchClient twitchClient;
    @InjectMocks
    private TwitchServiceImpl twitchService;

    @Test
    @DisplayName("When the recent followers channel null, throw null pointer")
    public void twitchRecentFollowers_null_channel() {
        assertThrows(NullPointerException.class, () -> twitchService.getRecentFollowers(null));
    }

    @Test
    @DisplayName("When the response from the twitch client recent followers is null, throw recent followers not found")
    public void twitchRecentFollowers_withOut_body() {
        String channel = "1234";
        TwitchApiUserResponse twitchApiUserResponse = new TwitchApiUserResponse();
        twitchApiUserResponse.setUsers(new TwitchUser[]{getValidTwitchUser()});
        when(twitchClient.getUser(channel)).thenReturn(new ResponseEntity<>(twitchApiUserResponse, OK));
        when(twitchClient.getFollowers(twitchApiUserResponse.getUsers()[0].getId())).thenReturn(new ResponseEntity<>(null, OK));
        assertThrows(TwitchFollowersNotFoundException.class, () -> twitchService.getRecentFollowers(channel));
    }

    @Test
    @DisplayName("When the response body from the twitch client recent followers is null, throw recent followers not found")
    public void twitchRecentFollowers_withOut_followers() {
        String channel = "1234";
        TwitchApiUserResponse twitchApiUserResponse = new TwitchApiUserResponse();
        twitchApiUserResponse.setUsers(new TwitchUser[]{getValidTwitchUser()});
        when(twitchClient.getUser(channel)).thenReturn(new ResponseEntity<>(twitchApiUserResponse, OK));
        TwitchApiFollowerResponse twitchApiFollowerResponse = new TwitchApiFollowerResponse();
        twitchApiFollowerResponse.setFollowers(null);
        when(twitchClient.getFollowers(twitchApiUserResponse.getUsers()[0].getId())).thenReturn(new ResponseEntity<>(twitchApiFollowerResponse, OK));
        assertEquals(0, twitchService.getRecentFollowers(channel).length);
    }

    @Test
    @DisplayName("When the recent followers are found, return followers")
    public void twitchRecentFollowers_with_followers() {
        String channel = "1234";
        TwitchApiUserResponse twitchApiUserResponse = new TwitchApiUserResponse();
        twitchApiUserResponse.setUsers(new TwitchUser[]{getValidTwitchUser()});
        when(twitchClient.getUser(channel)).thenReturn(new ResponseEntity<>(twitchApiUserResponse, OK));
        TwitchFollower[] twitchFollowers = getValidTwitchFollowerArray(10);
        TwitchApiFollowerResponse twitchApiFollowerResponse = new TwitchApiFollowerResponse();
        twitchApiFollowerResponse.setFollowers(twitchFollowers);
        when(twitchClient.getFollowers(twitchApiUserResponse.getUsers()[0].getId())).thenReturn(new ResponseEntity<>(twitchApiFollowerResponse, OK));
        assertArrayEquals(twitchFollowers, twitchService.getRecentFollowers(channel));
    }

    @Test
    @DisplayName("When the total followers channel null, throw null pointer")
    public void twitchTotalFollowers_null_channel() {
        assertThrows(NullPointerException.class, () -> twitchService.getTotalFollowers(null));
    }

    @Test
    @DisplayName("When the response from the twitch client total followers is null, throw recent followers not found")
    public void twitchTotalFollowers_withOut_body() {
        String channel = "1234";
        TwitchApiUserResponse twitchApiUserResponse = new TwitchApiUserResponse();
        twitchApiUserResponse.setUsers(new TwitchUser[]{getValidTwitchUser()});
        when(twitchClient.getUser(channel)).thenReturn(new ResponseEntity<>(twitchApiUserResponse, OK));
        when(twitchClient.getFollowers(twitchApiUserResponse.getUsers()[0].getId())).thenReturn(new ResponseEntity<>(null, OK));
        assertThrows(TwitchFollowersNotFoundException.class, () -> twitchService.getTotalFollowers(channel));
    }

    @Test
    @DisplayName("When the response body from the twitch client total followers is null, throw recent followers not found")
    public void twitchTotalFollowers_withOut_followers() {
        String channel = "1234";
        TwitchApiUserResponse twitchApiUserResponse = new TwitchApiUserResponse();
        twitchApiUserResponse.setUsers(new TwitchUser[]{getValidTwitchUser()});
        when(twitchClient.getUser(channel)).thenReturn(new ResponseEntity<>(twitchApiUserResponse, OK));
        TwitchApiFollowerResponse twitchApiFollowerResponse = new TwitchApiFollowerResponse();
        twitchApiFollowerResponse.setTotal(null);
        when(twitchClient.getFollowers(twitchApiUserResponse.getUsers()[0].getId())).thenReturn(new ResponseEntity<>(twitchApiFollowerResponse, OK));
        assertEquals((Long)0L, twitchService.getTotalFollowers(channel));
    }

    @Test
    @DisplayName("When the total followers are found, return followers")
    public void twitchTotalFollowers_with_followers() {
        Long total = 10L;
        String channel = "1234";
        TwitchApiUserResponse twitchApiUserResponse = new TwitchApiUserResponse();
        twitchApiUserResponse.setUsers(new TwitchUser[]{getValidTwitchUser()});
        when(twitchClient.getUser(channel)).thenReturn(new ResponseEntity<>(twitchApiUserResponse, OK));
        TwitchApiFollowerResponse twitchApiFollowerResponse = new TwitchApiFollowerResponse();
        twitchApiFollowerResponse.setTotal(total);
        when(twitchClient.getFollowers(twitchApiUserResponse.getUsers()[0].getId())).thenReturn(new ResponseEntity<>(twitchApiFollowerResponse, OK));
        assertEquals(total, twitchService.getTotalFollowers(channel));
    }

    @Test
    @DisplayName("When the user channel null, throw null pointer")
    public void twitchUser_null_channel() {
        assertThrows(NullPointerException.class, () -> twitchService.getUser(null));
    }

    @Test
    @DisplayName("When the user body null, throw twitch user not found")
    public void twitchUser_withOut_body() {
        String channel = "1234";
        when(twitchClient.getUser(channel)).thenReturn(new ResponseEntity<>(null, OK));
        assertThrows(TwitchUserNotFoundException.class, () -> twitchService.getUser(channel));
    }

    @Test
    @DisplayName("When the users are null, throw twitch user not found")
    public void twitchUser_withOut_users() {
        String channel = "1234";
        TwitchApiUserResponse twitchApiUserResponse = new TwitchApiUserResponse();
        twitchApiUserResponse.setUsers(null);
        when(twitchClient.getUser(channel)).thenReturn(new ResponseEntity<>(twitchApiUserResponse, OK));
        assertThrows(TwitchUserNotFoundException.class, () -> twitchService.getUser(channel));
    }

    @Test
    @DisplayName("When the user found but null, throw twitch user not found")
    public void twitchUser_with_nullUser() {
        String channel = "1234";
        TwitchApiUserResponse twitchApiUserResponse = new TwitchApiUserResponse();
        twitchApiUserResponse.setUsers(new TwitchUser[]{null});
        when(twitchClient.getUser(channel)).thenReturn(new ResponseEntity<>(twitchApiUserResponse, OK));
        assertThrows(TwitchUserNotFoundException.class, () -> twitchService.getUser(channel));
    }

    @Test
    @DisplayName("When the user found, return user")
    public void twitchUser_with_users() {
        TwitchUser twitchUser = getValidTwitchUser();
        String channel = "1234";
        TwitchApiUserResponse twitchApiUserResponse = new TwitchApiUserResponse();
        twitchApiUserResponse.setUsers(new TwitchUser[]{twitchUser});
        when(twitchClient.getUser(channel)).thenReturn(new ResponseEntity<>(twitchApiUserResponse, OK));
        assertEquals(twitchUser, twitchService.getUser(channel));
    }

    @Test
    @DisplayName("When the stream channel null, throw null pointer")
    public void twitchStream_null_channel() {
        assertThrows(NullPointerException.class, () -> twitchService.getStream(null));
    }

    @Test
    @DisplayName("When the stream body null, throw twitch stream not found")
    public void twitchStream_withOut_body() {
        String channel = "1234";
        when(twitchClient.getStream(channel)).thenReturn(new ResponseEntity<>(null, OK));
        assertThrows(TwitchStreamNotFoundException.class, () -> twitchService.getStream(channel));
    }

    @Test
    @DisplayName("When the streams are null, throw twitch stream not found")
    public void twitchStream_withOut_streams() {
        String channel = "1234";
        TwitchApiStreamResponse twitchApiStreamResponse = new TwitchApiStreamResponse();
        twitchApiStreamResponse.setStreams(null);
        when(twitchClient.getStream(channel)).thenReturn(new ResponseEntity<>(twitchApiStreamResponse, OK));
        assertThrows(TwitchStreamNotFoundException.class, () -> twitchService.getStream(channel));
    }

    @Test
    @DisplayName("When the first stream is null, throw twitch stream not found")
    public void twitchStream_with_nullStreams() {
        String channel = "1234";
        TwitchApiStreamResponse twitchApiStreamResponse = new TwitchApiStreamResponse();
        twitchApiStreamResponse.setStreams(new TwitchStream[]{null});
        when(twitchClient.getStream(channel)).thenReturn(new ResponseEntity<>(twitchApiStreamResponse, OK));
        assertThrows(TwitchStreamNotFoundException.class, () -> twitchService.getStream(channel));
    }

    @Test
    @DisplayName("When the stream is found, return the stream")
    public void twitchStream_with_streams() {
        String channel = "1234";
        TwitchStream twitchStream = getValidTwitchStream();
        TwitchApiStreamResponse twitchApiStreamResponse = new TwitchApiStreamResponse();
        twitchApiStreamResponse.setStreams(new TwitchStream[]{twitchStream});
        when(twitchClient.getStream(channel)).thenReturn(new ResponseEntity<>(twitchApiStreamResponse, OK));
        assertEquals(twitchStream, twitchService.getStream(channel));
    }
}
