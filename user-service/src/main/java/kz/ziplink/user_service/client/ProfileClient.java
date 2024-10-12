package kz.ziplink.user_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PROFILE-SERVICE")
public interface ProfileClient {

    @PostMapping("/profile")
    ResponseEntity<String> createProfile(@RequestBody String username);
}
