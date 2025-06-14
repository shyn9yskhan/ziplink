package kz.ziplink.search_index_aggregator_service.service;

import kz.ziplink.search_index_aggregator_service.client.BlockClient;
import kz.ziplink.search_index_aggregator_service.client.ProfileClient;
import kz.ziplink.search_index_aggregator_service.model.Block;
import kz.ziplink.search_index_aggregator_service.model.BlockEvent;
import kz.ziplink.search_index_aggregator_service.model.ProfileEvent;
import kz.ziplink.search_index_aggregator_service.model.ProfileSearchDocument;
import kz.ziplink.search_index_aggregator_service.repository.ProfileSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SearchIndexAggregatorServiceImpl implements SearchIndexAggregatorService {

    private final ProfileClient profileClient;
    private final BlockClient   blockClient;
    private final ProfileSearchRepository repository;

    @Autowired
    public SearchIndexAggregatorServiceImpl(ProfileClient profileClient,
                                            BlockClient blockClient,
                                            ProfileSearchRepository repository) {
        this.profileClient = profileClient;
        this.blockClient   = blockClient;
        this.repository    = repository;
    }

    @Override
    public void updateProfilePart(ProfileEvent event) {
        String username = profileClient
                .getProfile(event.getProfileId())
                .getBody()
                .getUsername();

        ProfileSearchDocument doc = repository.findById(username)
                .orElse(new ProfileSearchDocument(username, Collections.emptyList()));

        doc.setUsername(username);

        repository.save(doc);
    }

    @Override
    public void updateBlocksPart(BlockEvent event) {
        String username = profileClient
                .getProfile(event.getProfileId())
                .getBody()
                .getUsername();

        List<Block> blocks = blockClient
                .getBlocks(event.getProfileId())
                .getBody();

        ProfileSearchDocument doc = repository.findById(username)
                .orElse(new ProfileSearchDocument(username, Collections.emptyList()));

        doc.setBlocks(blocks != null ? blocks : Collections.emptyList());
        repository.save(doc);
    }
}
