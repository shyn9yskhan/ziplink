package kz.ziplink.profile_service.controller;

import kz.ziplink.profile_service.model.Profile;
import kz.ziplink.profile_service.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<String> createProfile(@RequestBody String username) {
        profileService.createProfile(username);
        return new ResponseEntity<>("Profile created", HttpStatus.CREATED);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getProfile(@PathVariable String profileId) {
        Profile profile = profileService.getProfile(profileId);
        if (profile != null) return new ResponseEntity<>(profile, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<String> updateProfile(@PathVariable String profileId, @RequestBody Profile updatedProfile) {
        if (profileService.updateProfile(profileId, updatedProfile)) return new ResponseEntity<>("Profile updated", HttpStatus.OK);
        else return new ResponseEntity<>("Profile not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<String> deleteProfile(@PathVariable String profileId) {
        boolean isDeleted = profileService.deleteProfile(profileId);
        if (isDeleted) return new ResponseEntity<>("Profile deleted", HttpStatus.OK);
        else return new ResponseEntity<>("Profile not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Profile> getProfileByUsername(@PathVariable String username) {
        Profile profile = profileService.getProfileByUsername(username);
        if (profile != null) return new ResponseEntity<>(profile, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/username/{username}/profileId")
    public ResponseEntity<String> getProfileIdByUsername(@PathVariable String username) {
        String profileId = profileService.getProfileIdByUsername(username);
        if (profileId != null) return new ResponseEntity<>(profileId, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
