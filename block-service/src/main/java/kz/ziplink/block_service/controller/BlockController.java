package kz.ziplink.block_service.controller;

import kz.ziplink.block_service.model.Block;
import kz.ziplink.block_service.service.BlockService;
import kz.ziplink.block_service.service.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/block")
public class BlockController {

    BlockService blockService;

    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }

    @PostMapping
    public ResponseEntity<String> createBlocks(@RequestParam String profileId) {
        blockService.createBlocks(profileId);
        return new ResponseEntity<>("Blocks created", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Block>> getBlocks(@RequestParam String profileId) {
        List<Block> blocks = blockService.getBlocks(profileId);
        return new ResponseEntity<>(blocks, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateBlocks(@RequestParam String profileId, @RequestBody List<Block> updatedBlocks) {
        if (blockService.updateBlocks(profileId, updatedBlocks)) return new ResponseEntity<>("Blocks updated", HttpStatus.OK);
        else return new ResponseEntity<>("Blocks not updated", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBlocks(@RequestParam String profileId) {
        if (blockService.deleteBlocks(profileId)) return new ResponseEntity<>("Blocks deleted", HttpStatus.OK);
        else return new ResponseEntity<>("Blocks not deleted", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/user/updateBlocks")
    public ResponseEntity<Map<String, String>> updateUserBlocks(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody List<Block> updatedBlocks) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(Map.of("message", "Unauthorized"), HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        String username = JwtService.extractUsername(token);

        if (username == null || username.isEmpty()) {
            return new ResponseEntity<>(Map.of("message", "Unauthorized"), HttpStatus.UNAUTHORIZED);
        }

        boolean updateSuccessful = blockService.updateUserBlocks(username, updatedBlocks);

        if (updateSuccessful) {
            return new ResponseEntity<>(Map.of("message", "Blocks updated successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("message", "Blocks not updated"), HttpStatus.BAD_REQUEST);
        }
    }
}
