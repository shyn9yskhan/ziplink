package kz.ziplink.authentication_service.config;

import kz.ziplink.authentication_service.client.UserClient;
import kz.ziplink.authentication_service.model.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResponseEntity<UserCredentials> response = userClient.getUserByUsername(username);
        Optional<UserCredentials> credentials = Optional.ofNullable(response.getBody());
        return credentials.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user " + username + " not found"));
    }
}
