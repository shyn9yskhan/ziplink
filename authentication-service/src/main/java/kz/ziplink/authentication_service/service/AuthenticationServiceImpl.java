package kz.ziplink.authentication_service.service;

import kz.ziplink.authentication_service.client.UserClient;
import kz.ziplink.authentication_service.model.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    UserClient userClient;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;

    public AuthenticationServiceImpl(UserClient userClient, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userClient = userClient;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public void registerUser(UserCredentials userCredentials) {
        userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
        userClient.createUser(userCredentials);
    }

    @Override
    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    @Override
    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
