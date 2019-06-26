package com.impurity.twitchwebintegrator.builder.steam;


import com.impurity.twitchwebintegrator.builder.steam.SteamUrlBuilder;
import com.impurity.twitchwebintegrator.test.utils.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.impurity.twitchwebintegrator.constant.Profiles.UNIT_TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author tmk2003
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles(UNIT_TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SteamUrlBuilderTests extends AbstractTest {

    @Test
    @DisplayName("When library url id is null, throw null pointer")
    public void steamUrl_library_null_id() {
        assertThrows(NullPointerException.class, () -> SteamUrlBuilder.buildLibraryURL("",null));
    }

    @Test
    @DisplayName("When library url key is null, throw null pointer")
    public void steamUrl_library_null_key() {
        assertThrows(NullPointerException.class, () -> SteamUrlBuilder.buildLibraryURL(null,""));
    }

    @Test
    @DisplayName("When library key supplied, key query param is valid")
    public void steamUrl_library_keyIsValid() {
        String key = "1234";
        assertEquals(key, SteamUrlBuilder.buildLibraryURL(key,"").build().getQueryParams().get("key").get(0));
    }

    @Test
    @DisplayName("When library id supplied, steamid query param is valid")
    public void steamUrl_library_steamIdIsValid() {
        String steamid = "1234";
        assertEquals(steamid, SteamUrlBuilder.buildLibraryURL("",steamid).build().getQueryParams().get("steamid").get(0));
    }

    @Test
    @DisplayName("When library is called, include_appinfo query param is 1")
    public void steamUrl_library_includeAppInfoIsValid() {
        assertEquals("1", SteamUrlBuilder.buildLibraryURL("","").build().getQueryParams().get("include_appinfo").get(0));
    }

}
