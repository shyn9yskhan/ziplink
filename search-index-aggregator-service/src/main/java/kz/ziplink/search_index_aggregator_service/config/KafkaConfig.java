package kz.ziplink.search_index_aggregator_service.config;

import kz.ziplink.search_index_aggregator_service.model.BlockEvent;
import kz.ziplink.search_index_aggregator_service.model.ProfileEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // 1) Настройка для BlockEvent
    @Bean
    public ConsumerFactory<String, BlockEvent> blockEventConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "search-index-aggregator-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // НЕ указываем JsonDeserializer.TRUSTED_PACKAGES здесь

        JsonDeserializer<BlockEvent> deserializer =
                new JsonDeserializer<>(BlockEvent.class, false);
        // доверяем только нашему пакету
        deserializer.addTrustedPackages("kz.ziplink.search_index_aggregator_service.model");

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BlockEvent>
    blockEventKafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, BlockEvent>();
        factory.setConsumerFactory(blockEventConsumerFactory());
        return factory;
    }

    // 2) Настройка для ProfileEvent
    @Bean
    public ConsumerFactory<String, ProfileEvent> profileEventConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "search-index-aggregator-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // НЕ указываем JsonDeserializer.TRUSTED_PACKAGES здесь

        JsonDeserializer<ProfileEvent> deserializer =
                new JsonDeserializer<>(ProfileEvent.class, false);
        deserializer.addTrustedPackages("kz.ziplink.search_index_aggregator_service.model");

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProfileEvent>
    profileEventKafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, ProfileEvent>();
        factory.setConsumerFactory(profileEventConsumerFactory());
        return factory;
    }
}
