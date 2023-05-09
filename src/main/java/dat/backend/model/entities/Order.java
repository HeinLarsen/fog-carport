package dat.backend.model.entities;


import java.util.ArrayList;

public class Order {
        private int orderID;
        private String timeStamp;
        private Status status;
        private ArrayList<AMaterial> materials;
}
