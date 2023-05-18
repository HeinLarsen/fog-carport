package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.services.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminEditOrderServlet", value = "/admineditorder")
public class AdminEditOrderServlet extends HttpServlet {

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
                request.setAttribute("orderbyid", order);
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
