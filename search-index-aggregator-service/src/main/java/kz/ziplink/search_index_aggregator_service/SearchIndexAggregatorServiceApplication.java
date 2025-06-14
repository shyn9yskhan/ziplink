package kz.ziplink.search_index_aggregator_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableElasticsearchRepositories(basePackages = "kz.ziplink.search_index_aggregator_service.repository")
public class SearchIndexAggregatorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchIndexAggregatorServiceApplication.class, args);
	}

}
