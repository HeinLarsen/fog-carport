package dat.backend.model.entities;

public enum status {
    pending("pending"),aproved("aproved"),cancelled("cancelled");


    private final String statusString;

    private status(String statusString) {

        this.statusString = statusString;
    }

    public String getStatusString() {
        return statusString;
    }
}
