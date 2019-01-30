package com.impurity.twitchwebintegrator.controller;

import com.impurity.twitchwebintegrator.model.TwitchFollower;
import com.impurity.twitchwebintegrator.model.TwitchStream;
import com.impurity.twitchwebintegrator.model.TwitchUser;
import com.impurity.twitchwebintegrator.service.TwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"https://tmk2003.github.io", "http://localhost:4200"}, maxAge = 3600)
@RequestMapping("/twitch")
@RestController
public class TwitchController {

    @Autowired
    private TwitchService twitchService;

    @GetMapping("/user/{channelName}")
    public TwitchUser getUser(
            @PathVariable("channelName") String channelName
    ) {
        return twitchService.getUser(channelName);
    }

    @GetMapping("/stream/{channelName}")
    public TwitchStream getStream(
            @PathVariable("channelName") String channelName
    ) {
        return twitchService.getStream(channelName);
    }

    @GetMapping("/followers/{channelName}/recent")
    public TwitchFollower[] getFollowers(
            @PathVariable("channelName") String channelName
    ) {
        return twitchService.getRecentFollowers(channelName);
    }

    @GetMapping("/followers/{channelName}/total")
    public Long getTotalFollowers(
            @PathVariable("channelName") String channelName
    ) {
        return twitchService.getTotalFollowers(channelName);
    }
}
