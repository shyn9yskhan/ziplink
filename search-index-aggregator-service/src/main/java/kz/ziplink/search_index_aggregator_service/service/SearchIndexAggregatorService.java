package kz.ziplink.search_index_aggregator_service.service;

import kz.ziplink.search_index_aggregator_service.model.BlockEvent;
import kz.ziplink.search_index_aggregator_service.model.ProfileEvent;

public interface SearchIndexAggregatorService {
    void updateProfilePart(ProfileEvent profileEvent);
    void updateBlocksPart(BlockEvent blockEvent);
}