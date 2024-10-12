package kz.ziplink.profile_service.repository;

import kz.ziplink.profile_service.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {
}
