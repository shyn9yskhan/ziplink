package kz.ziplink.profile_content_aggregator_service.client;

import kz.ziplink.profile_content_aggregator_service.model.Block;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "BLOCK-SERVICE")
public interface BlockClient {

    @GetMapping("/block")
    ResponseEntity<List<Block>> getBlocks(@RequestParam String profileId);
}
