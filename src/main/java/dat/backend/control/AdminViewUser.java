package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.services.OrderService;
import dat.backend.model.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminViewUser", value = "/adminviewuser")
public class AdminViewUser extends HttpServlet {

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
            if (u != null && u.getRoleId() == 2) {
                User userinfo = UserService.getUser(userId, connectionPool);
                List<Order> orderList = OrderService.getOrdersByUserId(userId, connectionPool);
                for (Order o : orderList) {
                    System.out.println(o);
                }
                request.setAttribute("orderList", orderList);
                request.setAttribute("userinfo", userinfo);

                request.getRequestDispatcher("WEB-INF/adminviewuser.jsp").forward(request, response);
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
