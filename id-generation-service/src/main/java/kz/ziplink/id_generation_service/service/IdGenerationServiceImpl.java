package kz.ziplink.id_generation_service.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGenerationServiceImpl implements IdGenerationService {
    @Override
    public String generateUserId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String generateProfileId() {
        return UUID.randomUUID().toString();
    }
}
