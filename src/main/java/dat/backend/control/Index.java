package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Carport;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.persistence.ConnectionPool;

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
        Order order = (Order) session.getAttribute("order");
        System.out.println("user: " + user);


        ArrayList<Integer> carportLength = new ArrayList<>();
        ArrayList<Integer> carportWidth = new ArrayList<>();

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

        request.setAttribute("length", carportLength);
        request.setAttribute("width", carportWidth);


        /*if (user == null) {
            //request.setAttribute("message", "Du skal være logget ind for at bestille");
            //request.getRequestDispatcher("login.jsp").forward(request, response);
           // return;
        if (order == null) {

            Carport carport = new Carport(order.getLength(), order.getWidth());
            session.setAttribute("order", order);
            request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
        }*/

        request.getRequestDispatcher("index.jsp").forward(request, response);


    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Order order = (Order) session.getAttribute("order");
            System.out.println("user: " + user);


            ArrayList<Integer> carportLength = new ArrayList<>();
            ArrayList<Integer> carportWidth = new ArrayList<>();

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

            request.setAttribute("length", carportLength);
            request.setAttribute("width", carportWidth);


        /*if (user == null) {
            //request.setAttribute("message", "Du skal være logget ind for at bestille");
            //request.getRequestDispatcher("login.jsp").forward(request, response);
           // return;
        if (order == null) {

            Carport carport = new Carport(order.getLength(), order.getWidth());
            session.setAttribute("order", order);
            request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
        }*/

            request.getRequestDispatcher("index.jsp").forward(request, response);


        }
    }
