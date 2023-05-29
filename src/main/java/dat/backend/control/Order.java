package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Carport;
import dat.backend.model.entities.Shed;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.services.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Order", value = "/order")
public class Order extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");


        try {

            int userId = Integer.parseInt(request.getParameter("id"));
            int carLength = Integer.parseInt(request.getParameter("carportlength"));
            int carWidth = Integer.parseInt(request.getParameter("carportwidth"));
            int shedL = Integer.parseInt(request.getParameter("shedlength"));
            int shedW = Integer.parseInt(request.getParameter("shedwidth"));

            Shed shed = new Shed(shedL, shedW);
            Carport carport = new Carport(carLength, carWidth, shed);

            Order o = (Order) session.getAttribute("order");
            OrderService.addOrder(userId, carport, connectionPool);
            if (o == null){
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("uservieworder?id=" + userId);

            }
        }catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

}

