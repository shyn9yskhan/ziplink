package kz.ziplink.block_service.service;

import kz.ziplink.block_service.client.ProfileClient;
import kz.ziplink.block_service.model.Block;
import kz.ziplink.block_service.model.Content;
import kz.ziplink.block_service.repository.BlockRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlockServiceImpl implements BlockService {

    BlockRepository blockRepository;
    ProfileClient profileClient;

    public BlockServiceImpl(BlockRepository blockRepository, ProfileClient profileClient) {
        this.blockRepository = blockRepository;
        this.profileClient = profileClient;
    }

    @Override
    public void createBlocks(String profileId) {
        List<Block> blocks = new ArrayList<>();
        Content content = new Content(profileId, blocks);
        blockRepository.save(content);
    }

    @Override
    public List<Block> getBlocks(String profileId) {
        Content content = blockRepository.findByProfileId(profileId).orElse(null);
        return content == null ? new ArrayList<>() : content.getBlocks();
    }

    @Override
    public boolean updateBlocks(String profileId, List<Block> updatedBlocks) {
        Content content = blockRepository.findByProfileId(profileId).orElse(null);
        if (content != null) {
            content.setBlocks(updatedBlocks);
            blockRepository.save(content);
            return true;
        }
        else return false;
    }

    @Override
    public boolean deleteBlocks(String profileId) {
        Content content = blockRepository.findByProfileId(profileId).orElse(null);
        if (content != null) {
            blockRepository.delete(content);
            return true;
        }
        else return false;
    }

    @Override
    public boolean updateUserBlocks(String username, List<Block> updatedBlocks) {
        String profileId = profileClient.getProfileIdByUsername(username).getBody();
        if (profileId != null) {
            Content content = blockRepository.findByProfileId(profileId).orElse(null);
            if (content != null) {
                content.setBlocks(updatedBlocks);
                blockRepository.save(content);
                return true;
            }
            else return false;
        }
        else return false;
    }
}
