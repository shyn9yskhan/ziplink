package kz.ziplink.block_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PROFILE-SERVICE")
public interface ProfileClient {

    @GetMapping("/profile/username/{username}/profileId")
    ResponseEntity<String> getProfileIdByUsername(@PathVariable String username);
}
