package kz.ziplink.profile_content_aggregator_service.service;

import kz.ziplink.profile_content_aggregator_service.model.ProfileContent;
import kz.ziplink.profile_content_aggregator_service.model.PublicProfileContent;

public interface ProfileContentAggregatorService {
    ProfileContent getProfileContent(String username);
    PublicProfileContent getPublicProfileContent(String username);
}