package kz.ziplink.profile_content_aggregator_service.model;

import java.util.List;

public class PublicProfileContent {

    private String username;
    private List<Block> blocks;

    public PublicProfileContent() {}

    public PublicProfileContent(String username, List<Block> blocks) {
        this.username = username;
        this.blocks = blocks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
}