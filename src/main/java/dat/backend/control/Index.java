package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Carport;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.Shed;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.services.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Index", value = "/index")
public class Index extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        ArrayList<Integer> carportLength = new ArrayList<>();
        ArrayList<Integer> carportWidth = new ArrayList<>();
        ArrayList<Integer> shedLength = new ArrayList<>();
        ArrayList<Integer> shedWidth = new ArrayList<>();

        carportLength.add(500);
        carportLength.add(520);
        carportLength.add(540);
        carportLength.add(560);
        carportLength.add(580);
        carportLength.add(600);
        carportLength.add(620);
        carportLength.add(640);
        carportLength.add(660);
        carportLength.add(680);
        carportLength.add(700);
        carportLength.add(720);
        carportLength.add(740);
        carportLength.add(760);
        carportLength.add(780);


        carportWidth.add(320);
        carportWidth.add(340);
        carportWidth.add(360);
        carportWidth.add(380);
        carportWidth.add(400);
        carportWidth.add(420);
        carportWidth.add(440);
        carportWidth.add(460);
        carportWidth.add(480);
        carportWidth.add(500);
        carportWidth.add(520);
        carportWidth.add(540);
        carportWidth.add(560);
        carportWidth.add(580);
        carportWidth.add(600);

        shedLength.add(0);
        shedLength.add(120);
        shedLength.add(140);
        shedLength.add(160);
        shedLength.add(180);
        shedLength.add(200);

        shedWidth.add(0);
        shedWidth.add(220);
        shedWidth.add(240);
        shedWidth.add(260);
        shedWidth.add(280);

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

        request.getRequestDispatcher("index.jsp").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request, response);
    }
}
