package com.impurity.twitchwebintegrator.controller;

import com.impurity.twitchwebintegrator.model.TwitchFollower;
import com.impurity.twitchwebintegrator.model.TwitchStream;
import com.impurity.twitchwebintegrator.model.TwitchUser;
import com.impurity.twitchwebintegrator.service.TwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tmk2003
 */
@CrossOrigin(origins = {"https://tmk2003.github.io", "http://localhost:4200"}, maxAge = 3600)
@RequestMapping("/twitch")
@RestController
public class TwitchController {

    @Autowired
    private TwitchService _twitchService;

    @GetMapping(value = "/user/{channelName}", produces = "application/json")
    public TwitchUser getUser(
            @PathVariable("channelName") String channelName
    ) {
        return _twitchService.getUser(channelName);
    }

    @GetMapping(value = "/stream/{channelName}", produces = "application/json")
    public TwitchStream getStream(
            @PathVariable("channelName") String channelName
    ) {
        return _twitchService.getStream(channelName);
    }

    @GetMapping(value = "/followers/{channelName}/recent", produces = "application/json")
    public TwitchFollower[] getFollowers(
            @PathVariable("channelName") String channelName
    ) {
        return _twitchService.getRecentFollowers(channelName);
    }

    @GetMapping(value = "/followers/{channelName}/total", produces = "application/json")
    public Long getTotalFollowers(
            @PathVariable("channelName") String channelName
    ) {
        return _twitchService.getTotalFollowers(channelName);
    }
}
