package dat.backend.model.entities;

public enum Status {
    Pending("pending"),Aproved("aproved"),Cancelled("cancelled");


    private final String statusString;

    private Status(String statusString) {

        this.statusString = statusString;
    }

    public String getStatusString() {
        return statusString;
    }
}



