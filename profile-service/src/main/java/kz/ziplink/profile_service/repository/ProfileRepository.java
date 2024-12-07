package kz.ziplink.profile_service.repository;

import kz.ziplink.profile_service.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, String> {
    Optional<Profile> findByUsername(String username);

    @Query("SELECT p.id FROM Profile p WHERE p.username = :username")
    Optional<String> findProfileIdByUsername(@Param("username") String username);
}
