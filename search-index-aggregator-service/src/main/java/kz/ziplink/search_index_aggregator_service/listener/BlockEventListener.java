package kz.ziplink.search_index_aggregator_service.listener;

import kz.ziplink.search_index_aggregator_service.model.BlockEvent;
import kz.ziplink.search_index_aggregator_service.service.SearchIndexAggregatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BlockEventListener {
    private static final Logger log = LoggerFactory.getLogger(BlockEventListener.class);
    private final SearchIndexAggregatorService searchIndexAggregatorService;

    public BlockEventListener(SearchIndexAggregatorService searchIndexAggregatorService) {
        this.searchIndexAggregatorService = searchIndexAggregatorService;
    }

    @KafkaListener(
            topics = "block-events",
            groupId = "search-index-aggregator-group",
            containerFactory = "blockEventKafkaListenerContainerFactory"
    )
    public void onBlockEvent(BlockEvent event) {
        log.info("BlockEventListener : Событие получено: {}", event);
        searchIndexAggregatorService.updateBlocksPart(event);
    }
}
