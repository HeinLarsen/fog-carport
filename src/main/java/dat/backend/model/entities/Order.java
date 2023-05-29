package dat.backend.model.entities;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Order {
        private int orderID;
        private Timestamp created;
        private Enum status;
        private int length;
        private int width;
        private int shedLength;
        private int shedWidth;
        private boolean shed;
        private ArrayList<OrderItem> orderItems;


        public Order(int orderID, Timestamp created, Enum status, int length, int width, boolean shed){
                this.orderID =orderID;
                this.created = created;
                this.status = status;
                this.length = length;
                this.width = width;
                this.shed = shed;

        }

        public Order(int orderID, Timestamp created, Enum status, int length, int width, boolean shed, int shedLength, int shedWidth){
                this.orderID =orderID;
                this.created = created;
                this.status = status;
                this.length = length;
                this.width = width;
                this.shed = shed;
                this.shedLength = shedLength;
                this.shedWidth = shedWidth;

        }

        public void addOrderItem(OrderItem orderItems){
                this.orderItems.add(orderItems);

        }

        public void addOrderItems(ArrayList<OrderItem> orderItems){
                this.orderItems = orderItems;

        }

        public int getLength() {
                return length;
        }

        public int getWidth() {
                return width;
        }

        public boolean isShed() {
                return shed;
        }

        public int getOrderID(){
                return orderID;

        }

        public Timestamp getCreated(){
                return created;

        }

        public Enum getStatus(){
                return status;

        }

        public ArrayList<OrderItem> getOrderItems(){
                return orderItems;
        }

        public void setStatus(String status) {
                this.status = Status.valueOf(status);
        }
}

