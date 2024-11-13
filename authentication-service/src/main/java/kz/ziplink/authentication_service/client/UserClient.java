package kz.ziplink.authentication_service.client;

import kz.ziplink.authentication_service.model.UserCredentials;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {

    @PostMapping("/user")
    ResponseEntity<String> createUser(@RequestBody UserCredentials userCredentials);

    @GetMapping("/user/username/{username}")
    ResponseEntity<UserCredentials> getUserByUsername(@PathVariable String username);
}
