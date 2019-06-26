package com.impurity.twitchwebintegrator.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.impurity.twitchwebintegrator.client.response.steam.SteamApiLibraryResponse;
import com.impurity.twitchwebintegrator.exception.RestTemplateServerException;
import com.impurity.twitchwebintegrator.exception.steam.SteamClientLibraryHttpRequestException;
import com.impurity.twitchwebintegrator.properties.SteamProperties;
import com.impurity.twitchwebintegrator.test.utils.AbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

import static com.impurity.twitchwebintegrator.builder.steam.SteamUrlBuilder.buildLibraryURL;
import static com.impurity.twitchwebintegrator.constant.Profiles.UNIT_TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

/**
 * @author tmk2003
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles(UNIT_TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SteamClientTests extends AbstractTest {

    @Autowired
    private SteamProperties steamProperties;
    @Autowired
    private SteamClient steamClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    public void setup() {
        mockServer = MockRestServiceServer.createServer(steamClient.getRestTemplate());
    }

    @Test
    @DisplayName("When the steam client library has null steamID, throw null pointer")
    public void steamClient_library_null_steamId() {
        assertThrows(NullPointerException.class, () -> steamClient.getLibrary(null));
    }

    @Test
    @DisplayName("When the steam client gets library, return response")
    public void steamClient_with_OK_steamApiLibraryResponse() throws JsonProcessingException {
        String steamId = "123";
        SteamApiLibraryResponse steamApiLibraryResponse = new SteamApiLibraryResponse();
        mockServer.expect(once(), requestTo(buildLibraryURL(steamProperties.getKey(), steamId).toUriString()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapToJson(steamApiLibraryResponse))
                );
        assertEquals(steamApiLibraryResponse, steamClient.getLibrary(steamId).getBody());
    }

    @Test
    @DisplayName("When the steam client library has client error, throw TwitchClientUserHttpRequestException")
    public void steamClient_with_CLIENTERROR_steamApiLibraryResponse() throws JsonProcessingException {
        String steamId = "123";
        SteamApiLibraryResponse steamApiLibraryResponse = new SteamApiLibraryResponse();
        mockServer.expect(once(), requestTo(buildLibraryURL(steamProperties.getKey(), steamId).toUriString()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapToJson(steamApiLibraryResponse))
                );
        assertThrows(SteamClientLibraryHttpRequestException.class, () -> steamClient.getLibrary(steamId));
    }

    @Test
    @DisplayName("When the steam client library has server error, throw RestTemplateServerException")
    public void steamClient_with_SERVERERROR_steamApiLibraryResponse() throws JsonProcessingException {
        String steamId = "123";
        SteamApiLibraryResponse steamApiLibraryResponse = new SteamApiLibraryResponse();
        mockServer.expect(once(), requestTo(buildLibraryURL(steamProperties.getKey(), steamId).toUriString()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapToJson(steamApiLibraryResponse))
                );
        assertThrows(RestTemplateServerException.class, () -> steamClient.getLibrary(steamId));
    }
}
