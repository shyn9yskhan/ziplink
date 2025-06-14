package kz.ziplink.search_index_aggregator_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import kz.ziplink.search_index_aggregator_service.model.Profile;

@FeignClient(name = "PROFILE-SERVICE")
public interface ProfileClient {

    @GetMapping("/profile/{profileId}")
    ResponseEntity<Profile> getProfile(@PathVariable String profileId);
}
