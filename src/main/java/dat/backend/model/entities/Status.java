package dat.backend.model.entities;

public enum Status {
    pending("PENDING"),aproved("aproved"),cancelled("cancelled");


    private final String statusString;

    private Status(String statusString) {

        this.statusString = statusString;
    }

    public String getStatusString() {
        return statusString;
    }
}
