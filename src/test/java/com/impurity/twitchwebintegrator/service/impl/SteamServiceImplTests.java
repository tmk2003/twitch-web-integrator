package com.impurity.twitchwebintegrator.service.impl;

import com.impurity.twitchwebintegrator.client.SteamClient;
import com.impurity.twitchwebintegrator.client.response.SteamApiLibraryResponse;
import com.impurity.twitchwebintegrator.domain.steam.SteamLibrary;
import com.impurity.twitchwebintegrator.domain.steam.SteamLibraryGame;
import com.impurity.twitchwebintegrator.exception.steam.SteamLibraryNotFoundException;
import com.impurity.twitchwebintegrator.test.utils.SteamFactory;
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

import java.util.Arrays;

import static com.impurity.twitchwebintegrator.constant.Profiles.UNIT_TEST;
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
public class SteamServiceImplTests {

    @Mock
    private SteamClient steamClient;
    @InjectMocks
    private SteamServiceImpl steamService;

    @Test
    @DisplayName("When the response from the steam client is null, throw library not found")
    public void steamLibrary_withOut_body() {
        String steamProfileId = "1234";
        when(steamClient.getLibrary(steamProfileId)).thenReturn(new ResponseEntity<>(null, OK));
        assertThrows(SteamLibraryNotFoundException.class, () -> steamService.getGameLibrary(steamProfileId));
    }

    @Test
    @DisplayName("When the response body from the steam client is null, throw library not found")
    public void steamLibrary_withOut_response() {
        String steamProfileId = "1234";
        SteamApiLibraryResponse response = new SteamApiLibraryResponse();
        response.setResponse(null);
        when(steamClient.getLibrary(steamProfileId)).thenReturn(new ResponseEntity<>(response, OK));
        assertThrows(SteamLibraryNotFoundException.class, () -> steamService.getGameLibrary(steamProfileId));
    }

    @Test
    @DisplayName("When the games and game count not initialized, return empty")
    public void steamLibrary_withOut_gamesAndCount() {
        String steamProfileId = "1234";
        SteamApiLibraryResponse response = new SteamApiLibraryResponse();
        SteamLibrary library = new SteamLibrary();
        response.setResponse(library);
        when(steamClient.getLibrary(steamProfileId)).thenReturn(new ResponseEntity<>(response, OK));
        assertEquals((Long) 0L, steamService.getGameLibrary(steamProfileId).getGameCount());
        assertArrayEquals(new SteamLibraryGame[0], steamService.getGameLibrary(steamProfileId).getGames());
    }

    @Test
    @DisplayName("When the game count is not initialized, return empty")
    public void steamLibrary_withOut_gameCount() {
        String steamProfileId = "1234";
        SteamApiLibraryResponse response = new SteamApiLibraryResponse();
        SteamLibrary library = new SteamLibrary();
        response.setResponse(library);
        when(steamClient.getLibrary(steamProfileId)).thenReturn(new ResponseEntity<>(response, OK));
        assertEquals((Long) 0L, steamService.getGameLibrary(steamProfileId).getGameCount());
    }

    @Test
    @DisplayName("When the games are not initialized, return empty")
    public void steamLibrary_withOut_games() {
        String steamProfileId = "1234";
        SteamApiLibraryResponse response = new SteamApiLibraryResponse();
        SteamLibrary library = new SteamLibrary();
        response.setResponse(library);
        when(steamClient.getLibrary(steamProfileId)).thenReturn(new ResponseEntity<>(response, OK));
        assertArrayEquals(new SteamLibraryGame[0], steamService.getGameLibrary(steamProfileId).getGames());
    }

    @Test
    @DisplayName("When the games and games count returned, return proper objects")
    public void steamLibrary_with_gamesAndCount() {
        String steamProfileId = "1234";
        SteamApiLibraryResponse response = new SteamApiLibraryResponse();
        SteamLibrary library = new SteamLibrary();
        library.setGames(SteamFactory.getValidSteamGameArray(10));
        library.setGameCount(10L);
        response.setResponse(library);
        when(steamClient.getLibrary(steamProfileId)).thenReturn(new ResponseEntity<>(response, OK));
        assertEquals(10L, steamService.getGameLibrary(steamProfileId).getGames().length);
        assertEquals((Long) 10L, steamService.getGameLibrary(steamProfileId).getGameCount());
    }

    @Test
    @DisplayName("When the games and games count returned, update icon urls")
    public void steamLibrary_update_iconUrls() {
        String steamProfileId = "1234";
        SteamApiLibraryResponse response = new SteamApiLibraryResponse();
        SteamLibrary library = new SteamLibrary();
        library.setGames(SteamFactory.getValidSteamGameArray(10));
        library.setGameCount(10L);
        response.setResponse(library);
        when(steamClient.getLibrary(steamProfileId)).thenReturn(new ResponseEntity<>(response, OK));
        Arrays.stream(steamService.getGameLibrary(steamProfileId).getGames()).forEach(
                game -> assertTrue(game.getImgIconUrl().matches("http://media.steampowered.com/steamcommunity/public/images/apps/\\w+/\\w+.jpg"))
        );
    }

    @Test
    @DisplayName("When the games and games count returned, update logo urls")
    public void steamLibrary_update_logoUrls() {
        String steamProfileId = "1234";
        SteamApiLibraryResponse response = new SteamApiLibraryResponse();
        SteamLibrary library = new SteamLibrary();
        library.setGames(SteamFactory.getValidSteamGameArray(10));
        library.setGameCount(10L);
        response.setResponse(library);
        when(steamClient.getLibrary(steamProfileId)).thenReturn(new ResponseEntity<>(response, OK));
        Arrays.stream(steamService.getGameLibrary(steamProfileId).getGames()).forEach(
                game -> assertTrue(game.getImgLogoUrl().matches("http://media.steampowered.com/steamcommunity/public/images/apps/\\w+/\\w+.jpg"))
        );
    }

    @Test
    @DisplayName("When the logo url null, throw null pointer")
    public void steamLibrary_null_logoUrls() {
        String steamProfileId = "1234";
        SteamApiLibraryResponse response = new SteamApiLibraryResponse();
        SteamLibrary library = new SteamLibrary();
        library.setGames(SteamFactory.getValidSteamGameArray(10));
        library.setGameCount(10L);
        Arrays.stream(library.getGames()).forEach(game -> game.setImgIconUrl(null));
        response.setResponse(library);
        when(steamClient.getLibrary(steamProfileId)).thenReturn(new ResponseEntity<>(response, OK));
        assertThrows(NullPointerException.class, () -> steamService.getGameLibrary(steamProfileId));
    }

    @Test
    @DisplayName("When the icon url null, throw null pointer")
    public void steamLibrary_null_iconUrls() {
        String steamProfileId = "1234";
        SteamApiLibraryResponse response = new SteamApiLibraryResponse();
        SteamLibrary library = new SteamLibrary();
        library.setGames(SteamFactory.getValidSteamGameArray(10));
        library.setGameCount(10L);
        Arrays.stream(library.getGames()).forEach(game -> game.setImgIconUrl(null));
        response.setResponse(library);
        when(steamClient.getLibrary(steamProfileId)).thenReturn(new ResponseEntity<>(response, OK));
        assertThrows(NullPointerException.class, () -> steamService.getGameLibrary(steamProfileId));
    }

    @Test
    @DisplayName("When the appId null, throw null pointer")
    public void steamLibrary_null_appId() {
        String steamProfileId = "1234";
        SteamApiLibraryResponse response = new SteamApiLibraryResponse();
        SteamLibrary library = new SteamLibrary();
        library.setGames(SteamFactory.getValidSteamGameArray(10));
        library.setGameCount(10L);
        Arrays.stream(library.getGames()).forEach(game -> game.setAppId(null));
        response.setResponse(library);
        when(steamClient.getLibrary(steamProfileId)).thenReturn(new ResponseEntity<>(response, OK));
        assertThrows(NullPointerException.class, () -> steamService.getGameLibrary(steamProfileId));
    }

    @Test
    @DisplayName("When the appId null for get steam library, throw null pointer")
    public void steamLibrary_null_steamProfileId() {
        assertThrows(NullPointerException.class, () -> steamService.getGameLibrary(null));
    }

    @Test
    @DisplayName("When the appId null for get steam library amount, throw null pointer")
    public void steamLibraryAmount_null_steamProfileId() {
        assertThrows(NullPointerException.class, () -> steamService.getGameLibraryAmount(null));
    }

    @Test
    @DisplayName("When the appId null, throw null pointer")
    public void steamLibraryAmount_null_count() {
        String steamProfileId = "1234";
        SteamApiLibraryResponse response = new SteamApiLibraryResponse();
        SteamLibrary library = new SteamLibrary();
        library.setGames(SteamFactory.getValidSteamGameArray(10));
        library.setGameCount(null);
        response.setResponse(library);
        when(steamClient.getLibrary(steamProfileId)).thenReturn(new ResponseEntity<>(response, OK));
        assertEquals((Long) 0L, steamService.getGameLibraryAmount(steamProfileId));
    }

    @Test
    @DisplayName("When the appId null, throw null pointer")
    public void steamLibraryAmount_with_count() {
        String steamProfileId = "1234";
        Long steamGameCount = 10L;
        SteamApiLibraryResponse response = new SteamApiLibraryResponse();
        SteamLibrary library = new SteamLibrary();
        library.setGames(SteamFactory.getValidSteamGameArray(10));
        library.setGameCount(steamGameCount);
        response.setResponse(library);
        when(steamClient.getLibrary(steamProfileId)).thenReturn(new ResponseEntity<>(response, OK));
        assertEquals(steamGameCount, steamService.getGameLibraryAmount(steamProfileId));
    }
}
