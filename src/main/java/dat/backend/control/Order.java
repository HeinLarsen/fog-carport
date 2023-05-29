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

        String carportLengthParam = request.getParameter("carportlength");
        String carportWidthParam = request.getParameter("carportwidth");
        String shedLengthParam = request.getParameter("shedlength");
        String shedWidthParam = request.getParameter("shedwidth");

        if (carportLengthParam == null || carportWidthParam == null || shedLengthParam == null || shedWidthParam == null ||
                carportLengthParam.isEmpty() || carportWidthParam.isEmpty() || shedLengthParam.isEmpty() || shedWidthParam.isEmpty()) {
            request.setAttribute("errorMessage", "Alle felter skal udfyldes.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }


        int carportLength = Integer.parseInt(carportLengthParam);
        int carportWidth = Integer.parseInt(carportWidthParam);
        int shedLength = Integer.parseInt(shedLengthParam);
        int shedWidth = Integer.parseInt(shedWidthParam);

        if (shedLength == 0 && shedWidth == 0) {
            if (user == null) {
                session.setAttribute("carportlength", carportLength);
                session.setAttribute("carportwidth", carportWidth);
                session.setAttribute("shedlength", shedLength);
                session.setAttribute("shedwidth", shedWidth);
                request.setAttribute("errorMessage", "Du skal være logget ind for at bestille.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                Shed shed = new Shed(shedLength, shedWidth);
                Carport carport = new Carport(carportLength, carportWidth, shed);
                User u = (User) session.getAttribute("user");
                int userId = Integer.parseInt(request.getParameter("id"));
                try {
                    OrderService.addOrder(userId, carport, connectionPool);
                    request.getRequestDispatcher("WEB-INF/success.jsp").forward(request, response);
                } catch (DatabaseException e) {
                    e.printStackTrace();
                }
            }
        } else if (shedLength == 0 || shedWidth == 0) {
                request.setAttribute("errorMessage", "Vælg venligst både skur-længde og skur-bredde.");

            }
        }

    }
