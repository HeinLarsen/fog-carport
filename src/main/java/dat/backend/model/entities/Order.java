package dat.backend.model.entities;


import java.util.ArrayList;

public class Order {
        private int orderID;
        private String timeStamp;
        private Status status;
        private ArrayList<OrderItem> orderItems;


        public Order(int orderID, String timeStamp, Status status){
                this.orderID =orderID;
                this.timeStamp = timeStamp;
                this.status = status;

        }

        public void addOrderItem(OrderItem orderItem){
                orderItem.add(orderItem);

        }

        public void addOrderItems(ArrayList<OrderItem> orderItems){
                this.orderItems.addAll(orderItems);

        }


        public int getOrderID(){
                return orderID;

        }

        public String getTimeStamp(){
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

        public void setTimeStamp()














}

