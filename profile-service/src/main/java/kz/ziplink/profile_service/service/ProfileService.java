package kz.ziplink.profile_service.service;

import kz.ziplink.profile_service.model.Profile;

public interface ProfileService {
    void createProfile(String username);
    Profile getProfile(String id);
    boolean updateProfile(String id, Profile updatedProfile);
    boolean deleteProfile(String id);
    Profile getProfileByUsername(String username);
    String getProfileIdByUsername(String username);
}
