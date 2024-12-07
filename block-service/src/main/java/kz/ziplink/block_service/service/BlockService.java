package kz.ziplink.block_service.service;

import kz.ziplink.block_service.model.Block;

import java.util.List;

public interface BlockService {
    void createBlocks(String profileId);
    List<Block> getBlocks(String profileId);
    boolean updateBlocks(String profileId, List<Block> updatedBlocks);
    boolean deleteBlocks(String profileId);
    boolean updateUserBlocks(String username, List<Block> updatedBlocks);
}
