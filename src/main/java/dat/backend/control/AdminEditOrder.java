package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.*;
import dat.backend.model.entities.Order;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.services.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminEditOrder", value = "/admineditorder")
public class AdminEditOrder extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException{
        this.connectionPool = ApplicationStart.getConnectionPool();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User u = (User) session.getAttribute("user");
        Order o = (Order) session.getAttribute("order");
        int roleId = Integer.parseInt(request.getParameter("id"));
        int orderId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("user", u);
        request.setAttribute("order", o);

        try {
            if (u != null && u.getRoleId() == 2) {
                Order order = OrderService.getOrderById(orderId, connectionPool);

                ArrayList<OrderItem> orderItemWood = new ArrayList<>();
                ArrayList<OrderItem> orderItemScrew = new ArrayList<>();
                ArrayList<OrderItem> orderItemFitting = new ArrayList<>();
                ArrayList<OrderItem> orderItemRoofTile = new ArrayList<>();

                for (OrderItem oi : order.getOrderItems()) {
                    if(oi.getMaterial() instanceof Wood){
                        orderItemWood.add(oi);
                    }else if(oi.getMaterial() instanceof Screw){
                        orderItemScrew.add(oi);
                    }else if(oi.getMaterial() instanceof Fitting) {
                        orderItemFitting.add(oi);
                    }else if(oi.getMaterial() instanceof RoofTile){
                        orderItemRoofTile.add(oi);
                    }
                }
                request.setAttribute("orderbyid", order);
                request.setAttribute("orderItemWood", orderItemWood);
                request.setAttribute("orderItemScrew", orderItemScrew);
                request.setAttribute("orderItemFitting", orderItemFitting);
                request.setAttribute("orderItemRoofTile", orderItemRoofTile);

                request.getRequestDispatcher("WEB-INF/admineditorder.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
