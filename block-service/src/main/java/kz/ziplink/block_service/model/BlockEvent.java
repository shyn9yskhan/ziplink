package kz.ziplink.block_service.model;

public class BlockEvent {
    private String profileId;
    private long timestamp;

    public BlockEvent() {}

    public BlockEvent(String profileId, long timestamp) {
        this.profileId = profileId;
        this.timestamp = timestamp;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}