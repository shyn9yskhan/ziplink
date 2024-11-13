package kz.ziplink.authentication_service.service;

import kz.ziplink.authentication_service.model.UserCredentials;

public interface AuthenticationService {
    void registerUser(UserCredentials userCredentials);
    String generateToken(String username);
    void validateToken(String token);
}
