package dat.backend.model.entities;

public enum Status {
    PENDING("pending"),APROVED("aproved"),CANCELLED("cancelled");


    private final String statusString;

    private Status(String statusString) {

        this.statusString = statusString;
    }

    public String getStatusString() {
        return statusString;
    }
}



