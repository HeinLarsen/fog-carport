package dat.backend.model.services;

import java.util.ArrayList;

public class OrderService {

    private List<Order> orders;
    private map<String, List<OrderItem>> orderItems;
    private MaterialFacade materialFacade;
    private OrderFacade orderFacade;

    public OrderService() {
        orders = new ArrayList<>();
        orderItems = new HashMap<>();
        orderFacade = new OrderFacade();
        materialFacade = new MaterialFacade();
    }

    //OBS om getOrder-metoden, skal indeholde ordreelementer og materialedata
    public List<Order> getOrder() {
        List<Order> OrderWithDetails = new ArrayList<>();

        // Iterér gennem hver ordre i orders-listen
        for (Order order : orders) {
            // Hent ordreelementer for den aktuelle ordre
            List<OrderItem> orderItem = orderItems.get(order.getId());

            // Iterér gennem hvert ordreelement og hent materialedata ved hjælp af MaterialFacade
            for (OrderItem item : orderItem) {
                item.setMaterial(materialFacade.getMaterial(item.getMaterialId()));
            }
            // Opdater ordrens ordreelementer med de modtagne materialedata
            order.setOrderItems(orderItem);
            // Tilføj den fulde ordre (inklusive ordreelementer og materialedata) til orderWithDetails-listen
            OrderWithDetajls.add(order);
        }

        return OrderWithDetails;
    }

    //I dette metode skal vi have regnet carports bredde samt længde ud, beregningsmetode kommer her.
    public void addOrder(Order order) {
        orders.add(order);
    }

    //Her kalder vi metoder fra Facaderne og Mapperne, for at Admin kan Approve en ordre.
    public void updateOrder(Order order) {
        //TODO
    }
}
