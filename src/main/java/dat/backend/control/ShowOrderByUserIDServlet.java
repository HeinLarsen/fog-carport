package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.persistence.OrderItemFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowOrderByUserIDServlet", value = "/showorderbyuserid")
public class ShowOrderByUserIDServlet extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        int userId = Integer.parseInt(request.getParameter("id"));
        try {
            if (u != null && u.getRole() == 2) {
                List<Order> orderList = OrderFacade.getOrdersByUserId(userId, connectionPool);
                for (Order o : orderList) {
                    System.out.println(o);
                    o.addOrderItemList(OrderItemFacade.getAllOrderItemsByOrderId(o.getId(), connectionPool));
                }
                request.setAttribute("orderList", orderList);
                request.getRequestDispatcher("WEB-INF/admineditorder.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
