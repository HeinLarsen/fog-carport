package dat.backend.control;

import dat.backend.model.entities.Carport;
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

        //tjek om shed er null, hvis ikke så sæt shed til true

        if(user == null){

            session.setAttribute("carportlength", carportLength);
            session.setAttribute("carportwidth", carportWidth);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            //error message
        }else{
            Carport carport = new Carport(carportLength, carportWidth);
            //husk shed
            

        }





    }
}
