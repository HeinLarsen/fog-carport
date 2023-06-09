package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.Status;
import dat.backend.model.entities.User;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.services.OrderService;
import dat.backend.model.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminHomepage", value = "/adminhomepage")
public class AdminHomepage extends HttpServlet {


    private ConnectionPool connectionPool;


    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        try {
            if (u != null && u.getRoleId() == 2){
                List<Order> orders = OrderService.getAllOrders(connectionPool);
                List<User> usersList = UserService.getAllUsers(connectionPool);
                List<Order> ordersPending = OrderService.getOrdersByStatus(Status.PENDING, connectionPool);


                request.setAttribute("ordersList", orders);
                request.setAttribute("usersList", usersList);
                request.setAttribute("ordersPending", ordersPending);

                request.getRequestDispatcher("WEB-INF/adminhome.jsp").forward(request, response);
            }else if (u != null && u.getRoleId() == 1){
                request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }


        }catch (Exception e){
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }
}
