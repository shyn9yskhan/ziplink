package kz.ziplink.search_index_aggregator_service.repository;

import kz.ziplink.search_index_aggregator_service.model.ProfileSearchDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProfileSearchRepository extends ElasticsearchRepository<ProfileSearchDocument, String> {}