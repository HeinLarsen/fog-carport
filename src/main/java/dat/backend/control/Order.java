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


        int carportLength = Integer.parseInt(request.getParameter("carportlength"));
        int carportWidth = Integer.parseInt(request.getParameter("carportwidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedlength"));
        int shedWidth = Integer.parseInt(request.getParameter("shedwidth"));


        session.setAttribute("carportlength", carportLength);
        session.setAttribute("carportwidth", carportWidth);
        session.setAttribute("shedlength", shedLength);
        session.setAttribute("shedwidth", shedWidth);


        if (shedLength == 0 && shedWidth == 0) {
            if (user == null) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                Carport carport = new Carport(carportLength, carportWidth);
                try {
                    OrderService.addOrder(user.getId(), carport, connectionPool);
                    request.getRequestDispatcher("WEB-INF/success.jsp").forward(request, response);
                } catch (DatabaseException e) {
                    e.printStackTrace();
                }
            }
        } else if(shedLength >= 120 && shedWidth >= 220) {
            if (user == null) {
                request.setAttribute("errorMessage", "Du skal være logget ind for at bestille.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                Shed shed = new Shed(shedLength, shedWidth);
                Carport carport = new Carport(carportLength, carportWidth, shed);
                try {
                    OrderService.addOrder(user.getId(), carport, connectionPool);
                    request.getRequestDispatcher("WEB-INF/success.jsp").forward(request, response);
                } catch (DatabaseException e) {
                    e.printStackTrace();
                }
            }
        } else if (shedLength == 0 || shedWidth == 0) {
            request.setAttribute("errorMessage", "Vælg venligst både skur-længde og skur-bredde.");
            request.getRequestDispatcher("error.jsp").forward(request, response);

        }
    }
}

