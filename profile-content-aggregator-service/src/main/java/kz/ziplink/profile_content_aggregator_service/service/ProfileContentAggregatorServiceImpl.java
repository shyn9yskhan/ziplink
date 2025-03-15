package kz.ziplink.profile_content_aggregator_service.service;

import kz.ziplink.profile_content_aggregator_service.client.BlockClient;
import kz.ziplink.profile_content_aggregator_service.client.ProfileClient;
import kz.ziplink.profile_content_aggregator_service.model.Block;
import kz.ziplink.profile_content_aggregator_service.model.Profile;
import kz.ziplink.profile_content_aggregator_service.model.ProfileContent;
import kz.ziplink.profile_content_aggregator_service.model.PublicProfileContent;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileContentAggregatorServiceImpl implements ProfileContentAggregatorService {
    ProfileClient profileClient;
    BlockClient blockClient;

    public ProfileContentAggregatorServiceImpl(ProfileClient profileClient, BlockClient blockClient) {
        this.profileClient = profileClient;
        this.blockClient = blockClient;
    }

    @Override
    public ProfileContent getProfileContent(String username) {

        Profile profile = profileClient.getProfileByUsername(username).getBody();
        if (profile == null) {
            throw new IllegalStateException("Profile not found for username: " + username);
        }

        List<Block> blocks = blockClient.getBlocks(profile.getId()).getBody();
        if (blocks == null) {
            blocks = new ArrayList<>(); // Пустой список вместо null
        }

        return new ProfileContent(profile, blocks);
    }

    @Override
    @Cacheable(value = "publicProfileContents", key = "#username", unless = "#result == null")
    public PublicProfileContent getPublicProfileContent(String username) {
        Profile profile = profileClient.getProfileByUsername(username).getBody();
        if (profile == null) {
            throw new IllegalStateException("Profile not found for username: " + username);
        }

        List<Block> blocks = blockClient.getBlocks(profile.getId()).getBody();
        if (blocks == null) {
            blocks = new ArrayList<>(); // Пустой список вместо null
        }

        return new PublicProfileContent(profile.getUsername(), blocks);
    }
}
