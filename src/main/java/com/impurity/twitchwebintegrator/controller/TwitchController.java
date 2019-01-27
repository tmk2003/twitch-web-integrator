package com.impurity.twitchwebintegrator.controller;

import com.impurity.twitchwebintegrator.model.TwitchFollower;
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

    @GetMapping("/user")
    public TwitchUser getUser(@RequestParam String channelName) {
        return twitchService.getUser(channelName);
    }

    @GetMapping("/followers/recent")
    public TwitchFollower[] getFollowers(@RequestParam String channelName) {
        return twitchService.getRecentFollowers(channelName);
    }

    @GetMapping("/followers/total")
    public Long getTotalFollowers(@RequestParam String channelName) {
        return twitchService.getTotalFollowers(channelName);
    }
}
