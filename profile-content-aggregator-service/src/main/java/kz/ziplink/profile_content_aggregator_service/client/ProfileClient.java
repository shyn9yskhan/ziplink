package kz.ziplink.profile_content_aggregator_service.client;

import kz.ziplink.profile_content_aggregator_service.model.Profile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PROFILE-SERVICE")
public interface ProfileClient {

    @GetMapping("/profile/username/{username}")
    ResponseEntity<Profile> getProfileByUsername(@PathVariable String username);
}