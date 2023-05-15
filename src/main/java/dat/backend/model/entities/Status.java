package dat.backend.model.entities;

public enum Status {
    pending("PENDING"),approved("APPROVED"),cancelled("CANCELLED");

    private final String statusString;

    Status(String statusString) {

        this.statusString = statusString;
    }

    public String getStatusString() {
        return statusString;
    }
}



