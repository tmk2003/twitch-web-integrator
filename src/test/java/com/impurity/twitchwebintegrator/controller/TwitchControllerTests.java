package com.impurity.twitchwebintegrator.controller;

import com.impurity.twitchwebintegrator.constant.Profiles;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchStreamException;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchStreamNotFoundException;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchUserException;
import com.impurity.twitchwebintegrator.exception.twitch.TwitchUserNotFoundException;
import com.impurity.twitchwebintegrator.model.TwitchStream;
import com.impurity.twitchwebintegrator.model.TwitchUser;
import com.impurity.twitchwebintegrator.service.TwitchService;
import com.impurity.twitchwebintegrator.test.utils.AbstractTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    private final String _mockChannelName = "abc123";

    /******************* Get Twitch User *******************/
    @Test
    public void whenGetTwitchUserCalledUserIsFoundAndReturned() throws Exception {
        TwitchUser twitchUser = new TwitchUser();

        when(_mockTwitchService.getUser(Mockito.anyString())).thenReturn(twitchUser);
        _mockMvc.perform(get("/twitch/user/" + _mockChannelName))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        mapToJson(twitchUser)
                ));
    }

    @Test
    public void whenGetTwitchUserCalledUserIsNotFoundAndReturn404() throws Exception {
        when(_mockTwitchService.getUser(Mockito.anyString())).thenThrow(TwitchUserNotFoundException.class);
        _mockMvc.perform(get("/twitch/user/" + _mockChannelName))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenGetTwitchUserCalledUserCantBeCreatedAndReturn500() throws Exception {
        when(_mockTwitchService.getUser(Mockito.anyString())).thenThrow(TwitchUserException.class);
        _mockMvc.perform(get("/twitch/user/" + _mockChannelName))
                .andExpect(status().isInternalServerError());
    }

    /******************* Get Twitch Stream *******************/
    @Test
    public void whenGetTwitchStreamCalledStreamIsFoundAndReturned() throws Exception {
        TwitchStream twitchStream = new TwitchStream();

        when(_mockTwitchService.getStream(Mockito.anyString())).thenReturn(twitchStream);
        _mockMvc.perform(get("/twitch/stream/" + _mockChannelName))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        mapToJson(twitchStream)
                ));
    }

    @Test
    public void whenGetTwitchStreamCalledStreamIsNotFoundAndReturn404() throws Exception {
        when(_mockTwitchService.getUser(Mockito.anyString())).thenThrow(TwitchStreamNotFoundException.class);
        _mockMvc.perform(get("/twitch/stream/" + _mockChannelName))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenGetTwitchStreamCalledStreamCantBeCreatedAndReturn500() throws Exception {
        when(_mockTwitchService.getUser(Mockito.anyString())).thenThrow(TwitchStreamException.class);
        _mockMvc.perform(get("/twitch/stream/" + _mockChannelName))
                .andExpect(status().isInternalServerError());
    }
}
