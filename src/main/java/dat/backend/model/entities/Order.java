package dat.backend.model.entities;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
        private int orderID;
        private LocalDateTime timeStamp;
        private Status status;
        private ArrayList<OrderItem> orderItems;


        public Order(int orderID, LocalDateTime timeStamp, Status status){
                this.orderID =orderID;
                this.timeStamp = timeStamp;
                this.status = status;

        }

        public void addOrderItem(OrderItem orderItems){
                this.orderItems.add(orderItems);

        }

        public void addOrderItems(ArrayList<OrderItem> orderItems){
                this.orderItems.addAll(orderItems);

        }


        public int getOrderID(){
                return orderID;

        }

        public LocalDateTime getTimeStamp(){
                return timeStamp;

        }

        public Status getStatus(){
                return status;

        }

        public ArrayList<OrderItem> getOrderItems(){
                return orderItems;

        }

        public void setOrderID(int orderID){
                this.orderID=orderID;

        }

        public void setTimeStamp(LocalDateTime timeStamp) {
                this.timeStamp = timeStamp;

        }

        public void setStatus(Status status){
                this.status = status;

        }

        public void setOrderItems(ArrayList<OrderItem> orderItems){
                this.orderItems = orderItems;
        }












}

