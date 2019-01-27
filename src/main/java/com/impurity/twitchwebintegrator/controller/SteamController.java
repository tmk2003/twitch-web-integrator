package com.impurity.twitchwebintegrator.controller;

import com.impurity.twitchwebintegrator.model.SteamGame;
import com.impurity.twitchwebintegrator.service.SteamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"https://tmk2003.github.io", "http://localhost:4200"}, maxAge = 3600)
@RequestMapping("/steam")
@RestController
public class SteamController {

    @Autowired
    private SteamService _steamService;

    @GetMapping("/library/{steamProfileId}")
    public SteamGame[] getGameLibrary(
            @PathVariable("steamProfileId") String steamProfileID
    ) {
        return _steamService.getGameLibrary(steamProfileID);
    }

    @GetMapping("/library/{steamProfileId}/amount")
    public Integer getGameLibraryAmount(
            @PathVariable("steamProfileId") String steamProfileID
    ) {
        return _steamService.getGameLibraryAmount(steamProfileID);
    }
}
