package kz.ziplink.user_service.repository;

import kz.ziplink.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
