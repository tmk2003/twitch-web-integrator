package com.impurity.twitchwebintegrator.controller;

import com.impurity.twitchwebintegrator.model.TwitchFollower;
import com.impurity.twitchwebintegrator.model.TwitchStream;
import com.impurity.twitchwebintegrator.model.TwitchUser;
import com.impurity.twitchwebintegrator.service.TwitchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author tmk2003
 */
@Api(value = "Twitch API endpoints", tags = {"Twitch"})
@CrossOrigin(origins = {"https://tmk2003.github.io", "http://localhost:4200"}, maxAge = 3600)
@RequestMapping("/twitch")
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
            value = "/{channelName}/user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public TwitchUser getUser(
            @PathVariable("channelName") String channelName
    ) {
        return twitchService.getUser(channelName);
    }

    @ApiOperation(value = "Returns Twitch Steam")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The twitch stream was found and successfully returned"),
            @ApiResponse(code = 404, message = "The twitch stream was not found")
    })
    @GetMapping(
            value = "/{channelName}/stream",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public TwitchStream getStream(
            @PathVariable("channelName") String channelName
    ) {
        return twitchService.getStream(channelName);
    }

    @ApiOperation(value = "Returns Twitch User's recent followers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The twitch recent followers were found and successfully returned"),
            @ApiResponse(code = 404, message = "The twitch recent followers were not found")
    })
    @GetMapping(
            value = "/{channelName}/followers/recent",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public TwitchFollower[] getFollowers(
            @PathVariable("channelName") String channelName
    ) {
        return twitchService.getRecentFollowers(channelName);
    }

    @ApiOperation(value = "Returns Twitch User's total follows")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The twitch user total followers were found and successfully returned"),
            @ApiResponse(code = 404, message = "The twitch user total followers were not found")
    })
    @GetMapping(
            value = "/{channelName}/followers/total",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Long getTotalFollowers(
            @PathVariable("channelName") String channelName
    ) {
        return twitchService.getTotalFollowers(channelName);
    }
}
