package kz.ziplink.profile_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "BLOCK-SERVICE")
public interface BlockClient {

    @PostMapping("/block")
    ResponseEntity<String> createBlocks(@RequestParam String profileId);
}
