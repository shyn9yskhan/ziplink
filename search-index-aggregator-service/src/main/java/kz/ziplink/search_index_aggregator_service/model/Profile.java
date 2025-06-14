package kz.ziplink.search_index_aggregator_service.model;

public class Profile {
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