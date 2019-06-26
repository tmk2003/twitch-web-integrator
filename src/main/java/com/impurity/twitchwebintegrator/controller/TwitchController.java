package com.impurity.twitchwebintegrator.controller;

import com.impurity.twitchwebintegrator.response.TwitchFollowersResponse;
import com.impurity.twitchwebintegrator.response.TwitchStreamResponse;
import com.impurity.twitchwebintegrator.response.TwitchUserResponse;
import com.impurity.twitchwebintegrator.service.TwitchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author tmk2003
 */
@Api(value = "Twitch API endpoints", tags = {"Twitch"})
@CrossOrigin(origins = {"https://tmk2003.github.io", "http://localhost:4200"}, maxAge = 3600)
@RequestMapping("/twitch/v1")
@RestController
public class TwitchController {

    @Autowired
    private TwitchService twitchService;

    @ApiOperation(value = "Returns Twitch User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The twitch user was found and successfully returned"),
            @ApiResponse(code = 404, message = "The twitch user was not found")
    })
    @GetMapping(
            value = "/users/{channelName}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public TwitchUserResponse getUser(
            @PathVariable("channelName") String channelName
    ) {
        TwitchUserResponse twitchUserResponse = new TwitchUserResponse();
        twitchUserResponse.setUser(twitchService.getUser(channelName));
        return twitchUserResponse;
    }

    @ApiOperation(value = "Returns Twitch Steam")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The twitch stream was found and successfully returned"),
            @ApiResponse(code = 404, message = "The twitch stream was not found")
    })
    @GetMapping(
            value = "/users/{channelName}/streams",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public TwitchStreamResponse getStream(
            @PathVariable("channelName") String channelName
    ) {
        TwitchStreamResponse twitchStreamResponse = new TwitchStreamResponse();
        twitchStreamResponse.setStream(twitchService.getStream(channelName));
        return twitchStreamResponse;
    }

    @ApiOperation(value = "Returns Twitch User's recent followers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The twitch recent followers were found and successfully returned"),
            @ApiResponse(code = 404, message = "The twitch recent followers were not found")
    })
    @GetMapping(
            value = "/users/{channelName}/followers",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public TwitchFollowersResponse getFollowers(
            @PathVariable("channelName") String channelName
    ) {
        TwitchFollowersResponse twitchStreamResponse = new TwitchFollowersResponse();
        twitchStreamResponse.setFollowers(twitchService.getRecentFollowers(channelName));
        return twitchStreamResponse;
    }

    @ApiOperation(value = "Returns Twitch User's total follows")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The twitch user total followers were found and successfully returned"),
            @ApiResponse(code = 404, message = "The twitch user total followers were not found")
    })
    @GetMapping(
            value = "/users/{channelName}/followers/total",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public Long getTotalFollowers(
            @PathVariable("channelName") String channelName
    ) {
        return twitchService.getTotalFollowers(channelName);
    }
}
