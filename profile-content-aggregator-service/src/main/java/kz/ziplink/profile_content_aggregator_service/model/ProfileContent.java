package kz.ziplink.profile_content_aggregator_service.model;

import java.util.List;

public class ProfileContent {

    private Profile profile;
    private List<Block> blocks;

    public ProfileContent() {}

    public ProfileContent(Profile profile, List<Block> blocks) {
        this.profile = profile;
        this.blocks = blocks;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
}