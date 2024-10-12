package kz.ziplink.block_service.controller;

import kz.ziplink.block_service.model.Block;
import kz.ziplink.block_service.service.BlockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (!blocks.isEmpty()) return new ResponseEntity<>(blocks, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
}
