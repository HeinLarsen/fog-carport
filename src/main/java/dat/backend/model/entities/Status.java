package dat.backend.model.entities;

public enum Status {
    PENDING("pending"),APPROVED("approved"),CANCELLED("cancelled");

    private final String statusString;

    Status(String statusString) {

        this.statusString = statusString;
    }

    public String getStatusString() {
        return statusString;
    }
}



