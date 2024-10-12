package kz.ziplink.block_service.repository;

import kz.ziplink.block_service.model.Content;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlockRepository extends MongoRepository<Content, String> {
    Optional<Content> findById(String id);
    Optional<Content> findByProfileId(String profileId);
}
