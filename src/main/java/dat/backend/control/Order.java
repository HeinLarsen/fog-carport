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

        request.setAttribute("length", carportLength);
        request.setAttribute("width", carportWidth);
        request.setAttribute("shedLength", shedLength);
        request.setAttribute("shedWidth", shedWidth);


        int carLength = Integer.parseInt(request.getParameter("carportlength"));
        int carWidth = Integer.parseInt(request.getParameter("carportwidth"));
        int shedL = Integer.parseInt(request.getParameter("shedlength"));
        int shedW = Integer.parseInt(request.getParameter("shedwidth"));

        if (shedL == 0 && shedW == 0) {
            if (user == null) {

                session.setAttribute("carportlength", carportLength);
                session.setAttribute("carportwidth", carportWidth);
                session.setAttribute("shedlength", shedLength);
                session.setAttribute("shedwidth", shedWidth);

                request.setAttribute("errorMessage", "Du skal være logget ind for at bestille.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                Shed shed = new Shed(shedL, shedW);
                Carport carport = new Carport(carLength, carWidth, shed);
                User u = (User) session.getAttribute("user");
                int userId = Integer.parseInt(request.getParameter("id"));
                try {
                    OrderService.addOrder(userId, carport, connectionPool);
                    request.getRequestDispatcher("WEB-INF/success.jsp").forward(request, response);
                } catch (DatabaseException e) {
                    e.printStackTrace();
                }
            }
        } else if (shedL == 0 || shedW == 0) {
            request.setAttribute("errorMessage", "Vælg venligst både skur-længde og skur-bredde.");

        }

}

