package kz.ziplink.user_service.service;

import kz.ziplink.user_service.client.IdGenerationClient;
import kz.ziplink.user_service.client.ProfileClient;
import kz.ziplink.user_service.model.User;
import kz.ziplink.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    IdGenerationClient idGenerationClient;
    ProfileClient profileClient;

    public UserServiceImpl(UserRepository userRepository, IdGenerationClient idGenerationClient, ProfileClient profileClient) {
        this.userRepository = userRepository;
        this.idGenerationClient = idGenerationClient;
        this.profileClient = profileClient;
    }

    @Override
    public void createUser(User user) {
        String generatedUserId = idGenerationClient.generateUserId().getBody();
        user.setId(generatedUserId);
        userRepository.save(user);

        profileClient.createProfile(user.getUsername());
    }

    @Override
    public User getUser(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateUser(String id, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());

            userRepository.save(user);
            return true;
        }
        else return false;
    }

    @Override
    public boolean deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        else return false;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
