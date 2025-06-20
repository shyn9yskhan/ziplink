package kz.ziplink.profile_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Profile {
    @Id
    private String id;
    private String username;

    public Profile() {}

    public Profile(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
}
