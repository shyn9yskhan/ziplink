package kz.ziplink.search_index_aggregator_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "profiles")
public class ProfileSearchDocument {

    @Id
    private String username;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Block> blocks;

    public ProfileSearchDocument() {}

    public ProfileSearchDocument(String username, List<Block> blocks) {
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