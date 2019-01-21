package com.impurity.twitchwebintegrator.controller;

import com.impurity.twitchwebintegrator.model.TwitchFollower;
import com.impurity.twitchwebintegrator.model.TwitchUser;
import com.impurity.twitchwebintegrator.service.TwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"https://tmk2003.github.io", "http://localhost:4200"}, maxAge = 3600)
@RestController
public class TwitchController {

    @Autowired
    private TwitchService twitchService;

    @GetMapping("/user")
    public TwitchUser getUser(@RequestParam String channelName) {
        return twitchService.getUser(channelName);
    }

    @GetMapping("/followers")
    public TwitchFollower[] getFollowers(@RequestParam String channelName) {
        return twitchService.getFollowers(channelName);
    }

}
