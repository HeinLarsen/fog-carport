package dat.backend.model.entities;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Order {
        private int orderID;
        private Timestamp timeStamp;
        private Enum status;
        private ArrayList<OrderItem> orderItems;


        public Order(int orderID, Timestamp timeStamp, Enum status){
                this.orderID =orderID;
                this.timeStamp = timeStamp;
                this.status = status;

        }

        public void addOrderItem(OrderItem orderItems){
                this.orderItems.add(orderItems);

        }

        public void addOrderItems(ArrayList<OrderItem> orderItems){
                this.orderItems = orderItems;

        }


        public int getOrderID(){
                return orderID;

        }

        public Timestamp getTimeStamp(){
                return timeStamp;

        }

        public Enum getStatus(){
                return status;

        }

        public ArrayList<OrderItem> getOrderItems(){
                return orderItems;
        }









}

