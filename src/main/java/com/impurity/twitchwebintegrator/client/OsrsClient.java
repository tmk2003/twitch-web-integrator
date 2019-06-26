package com.impurity.twitchwebintegrator.client;

import com.impurity.twitchwebintegrator.exception.RestTemplateClientException;
import com.impurity.twitchwebintegrator.exception.osrs.OsrsItemHttpRequestException;
import com.impurity.twitchwebintegrator.exception.osrs.OsrsPlayerHttpRequestException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.impurity.twitchwebintegrator.builder.osrs.OsrsUrlBuilder.buildGrandExchangeURL;
import static com.impurity.twitchwebintegrator.builder.osrs.OsrsUrlBuilder.buildPlayerURL;

/**
 * @author tmk2003
 */
@Slf4j
@Component
public class OsrsClient extends RestTemplateClient {

    @Override
    protected HttpHeaders getHeaders() {
        return new HttpHeaders();
    }

    /**
     * Get a OSRS player based off he player name
     *
     * @param player - Player name
     * @return - Player hiscores
     */
    public ResponseEntity<String> getPlayer(@NonNull final String player) {
        try {
            return getRequest(
                    buildPlayerURL(player).toUriString(),
                    HttpMethod.GET,
                    new HttpEntity(this.getHeaders()),
                    String.class
            );
        } catch (RestTemplateClientException ex) {
            log.error("Osrs Client Issues: {}", ex.getMessage());
            throw new OsrsPlayerHttpRequestException("Cannot get player", ex.getStatus(), ex);
        }
    }

    /**
     * Get a OSRS item based off he item id
     *
     * @param itemId - Item id
     * @return - Player item
     */
    public ResponseEntity<String> getItem(@NonNull final Long itemId) {
        try {
            return getRequest(
                    buildGrandExchangeURL(itemId).toUriString(),
                    HttpMethod.GET,
                    new HttpEntity(this.getHeaders()),
                    String.class
            );
        } catch (RestTemplateClientException ex) {
            log.error("Osrs Client Issues: {}", ex.getMessage());
            throw new OsrsItemHttpRequestException("Cannot get item", ex.getStatus(), ex);
        }
    }
}
