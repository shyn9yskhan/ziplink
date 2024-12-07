package kz.ziplink.profile_service.service;

import kz.ziplink.profile_service.client.BlockClient;
import kz.ziplink.profile_service.client.IdGenerationClient;
import kz.ziplink.profile_service.model.Profile;
import kz.ziplink.profile_service.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    ProfileRepository profileRepository;
    IdGenerationClient idGenerationClient;
    BlockClient blockClient;

    public ProfileServiceImpl(ProfileRepository profileRepository, IdGenerationClient idGenerationClient, BlockClient blockClient) {
        this.profileRepository = profileRepository;
        this.idGenerationClient = idGenerationClient;
        this.blockClient = blockClient;
    }

    @Override
    public void createProfile(String username) {
        Profile profile = new Profile();
        String generatedProfileId = idGenerationClient.generateProfileId().getBody();
        profile.setId(generatedProfileId);
        profile.setUsername(username);
        profileRepository.save(profile);

        blockClient.createBlocks(generatedProfileId);
    }

    @Override
    public Profile getProfile(String id) {
        return profileRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateProfile(String id, Profile updatedProfile) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();

            profile.setUsername(updatedProfile.getUsername());

            profileRepository.save(profile);
            return true;
        }
        else return false;
    }

    @Override
    public boolean deleteProfile(String id) {
        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
            return true;
        }
        else return false;
    }

    @Override
    public Profile getProfileByUsername(String username) {
        return profileRepository.findByUsername(username).orElse(null);
    }

    @Override
    public String getProfileIdByUsername(String username) {
        return profileRepository.findProfileIdByUsername(username).orElse(null);
    }
}
