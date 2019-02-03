package com.impurity.twitchwebintegrator.controller;

import com.impurity.twitchwebintegrator.exception.TwitchUserException;
import com.impurity.twitchwebintegrator.exception.TwitchUserNotFoundException;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("unit-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TwitchControllerTests extends AbstractTest {

    @MockBean
    private TwitchService _mockTwitchService;
    @Autowired
    private MockMvc _mockMvc;
    private final String _mockChannelName = "abc123";


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
}
