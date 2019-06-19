package com.impurity.twitchwebintegrator.controller;

import com.impurity.twitchwebintegrator.domain.SteamLibrary;
import com.impurity.twitchwebintegrator.response.SteamLibraryResponse;
import com.impurity.twitchwebintegrator.service.SteamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tmk2003
 */
@Api(value = "Steam API endpoints", tags = {"Steam"})
@CrossOrigin(origins = {"https://tmk2003.github.io", "http://localhost:4200"}, maxAge = 3600)
@RequestMapping("/steam")
@RestController
public class SteamController {

    @Autowired
    private SteamService steamService;

    @ApiOperation(value = "Returns Steam library")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The steam library was found and successfully returned"),
            @ApiResponse(code = 404, message = "The steam library was not found")
    })
    @GetMapping("/{steamProfileId}/library")
    public SteamLibraryResponse getGameLibrary(
            @PathVariable("steamProfileId") String steamProfileID
    ) {
        SteamLibrary steamLibrary = steamService.getGameLibrary(steamProfileID);
        SteamLibraryResponse steamLibraryResponse = new SteamLibraryResponse();
        steamLibraryResponse.setGames(steamLibrary.getGames());
        return steamLibraryResponse;
    }

    @ApiOperation(value = "Returns Steam library amount")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The steam library amount was found and successfully returned"),
            @ApiResponse(code = 404, message = "The steam library amount was not found")
    })
    @GetMapping("/{steamProfileId}/library/amount")
    public Long getGameLibraryAmount(
            @PathVariable("steamProfileId") String steamProfileID
    ) {
        return steamService.getGameLibraryAmount(steamProfileID);
    }
}
