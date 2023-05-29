package dat.backend.control;

import dat.backend.model.entities.Carport;
import dat.backend.model.entities.Shed;
import dat.backend.model.entities.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Order", value = "/order")
public class Order extends HttpServlet {
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

        if (shedLength == 0 || shedWidth == 0) {
            request.setAttribute("errorMessage", "Vælg venligst både shed-længde og shed-bredde.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            if(user == null){

                session.setAttribute("carportlength", carportLength);
                session.setAttribute("carportwidth", carportWidth);
                session.setAttribute("shedlength", shedLength);
                session.setAttribute("shedwidth", shedWidth);
                request.setAttribute("errorMessage", "Du skal være logget ind for at bestille.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }else{
                Shed shed = new Shed(shedLength, shedWidth);
                Carport carport = new Carport(carportLength, carportWidth, shed);

            }
        }
        
    }
}
