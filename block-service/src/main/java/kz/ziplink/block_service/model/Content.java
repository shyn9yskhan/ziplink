package kz.ziplink.block_service.model;

import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("content")
public class Content {
    @Id
    private String id;
    private String profileId;
    private List<Block> blocks;

    public Content(String profileId, List<Block> blocks) {
        this.profileId = profileId;
        this.blocks = blocks;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getProfileId() { return profileId; }

    public void setProfileId(String profileId) { this.profileId = profileId; }

    public List<Block> getBlocks() { return blocks; }

    public void setBlocks(List<Block> blocks) { this.blocks = blocks; }
}
