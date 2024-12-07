package kz.ziplink.profile_content_aggregator_service.controller;

import kz.ziplink.profile_content_aggregator_service.model.ProfileContent;
import kz.ziplink.profile_content_aggregator_service.model.PublicProfileContent;
import kz.ziplink.profile_content_aggregator_service.service.JwtService;
import kz.ziplink.profile_content_aggregator_service.service.ProfileContentAggregatorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile-content")
public class ProfileContentAggregatorController {

    ProfileContentAggregatorService profileContentAggregatorService;

    public ProfileContentAggregatorController(ProfileContentAggregatorService profileContentAggregatorService) {
        this.profileContentAggregatorService = profileContentAggregatorService;
    }

    @GetMapping
    public ResponseEntity<ProfileContent> getProfileContent(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        String username = JwtService.extractUsername(token);

        if (username == null || username.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        ProfileContent profileContent = profileContentAggregatorService.getProfileContent(username);
        if (profileContent != null) return new ResponseEntity<>(profileContent, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/public/{username}")
    public ResponseEntity<PublicProfileContent> getPublicProfileContent(@PathVariable String username) {
        PublicProfileContent publicProfileContent = profileContentAggregatorService.getPublicProfileContent(username);

        if (publicProfileContent != null) {
            return new ResponseEntity<>(publicProfileContent, HttpStatus.OK);
        }
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}