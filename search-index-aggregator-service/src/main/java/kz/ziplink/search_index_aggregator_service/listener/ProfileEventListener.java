package kz.ziplink.search_index_aggregator_service.listener;

import kz.ziplink.search_index_aggregator_service.model.ProfileEvent;
import kz.ziplink.search_index_aggregator_service.service.SearchIndexAggregatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProfileEventListener {
    private static final Logger log = LoggerFactory.getLogger(ProfileEventListener.class);
    private final SearchIndexAggregatorService searchIndexAggregatorService;

    public ProfileEventListener(SearchIndexAggregatorService searchIndexAggregatorService) {
        this.searchIndexAggregatorService = searchIndexAggregatorService;
    }

    @KafkaListener(
            topics = "profile-events",
            groupId = "search-index-aggregator-group",
            containerFactory = "profileEventKafkaListenerContainerFactory"
    )
    public void onProfileEvent(ProfileEvent event) {
        log.info("ProfileEventListener : Событие получено: {}", event);
        searchIndexAggregatorService.updateProfilePart(event);
    }
}
