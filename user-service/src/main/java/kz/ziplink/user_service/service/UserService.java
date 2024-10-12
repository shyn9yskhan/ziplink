package kz.ziplink.user_service.service;

import kz.ziplink.user_service.model.User;

public interface UserService {
    void createUser(User user);
    User getUser(String id);
    boolean updateUser(String id, User updatedUser);
    boolean deleteUser(String id);
}
