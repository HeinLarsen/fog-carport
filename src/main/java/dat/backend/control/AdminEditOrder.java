package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.*;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.services.OrderService;
import dat.backend.model.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebServlet(name = "AdminEditOrder", value = "/admineditorder")
public class AdminEditOrder extends HttpServlet {

    private ConnectionPool connectionPool;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);


    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User u = (User) session.getAttribute("user");
        int orderId = Integer.parseInt(request.getParameter("id"));
        int userId = Integer.parseInt(request.getParameter("userid"));



        request.setAttribute("user", u);


        try {
            if (u != null && u.getRoleId() == 2) {
                User userinfo = UserService.getUser(userId, connectionPool);
                Order order = OrderService.getOrderById(orderId, connectionPool);



                ArrayList<OrderItem> orderItemWood = new ArrayList<>();
                ArrayList<OrderItem> orderItemScrew = new ArrayList<>();
                ArrayList<OrderItem> orderItemFitting = new ArrayList<>();
                ArrayList<OrderItem> orderItemRoofTile = new ArrayList<>();

                for (OrderItem oi : order.getOrderItems()) {
                    if (oi.getMaterial() instanceof Wood) {
                        orderItemWood.add(oi);
                    } else if (oi.getMaterial() instanceof Screw) {
                        orderItemScrew.add(oi);
                    } else if (oi.getMaterial() instanceof Fitting) {
                        orderItemFitting.add(oi);
                    } else if (oi.getMaterial() instanceof RoofTile) {
                        orderItemRoofTile.add(oi);
                    }
                }

                request.setAttribute("orderbyid", order);
                request.setAttribute("orderItemWood", orderItemWood);
                request.setAttribute("orderItemScrew", orderItemScrew);
                request.setAttribute("orderItemFitting", orderItemFitting);
                request.setAttribute("orderItemRoofTile", orderItemRoofTile);
                request.setAttribute("user", userinfo);



                request.getRequestDispatcher("WEB-INF/admineditorder.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User u = (User) session.getAttribute("user");

        String status = request.getParameter("status");
        int orderId = Integer.parseInt(request.getParameter("order"));
        int userId = Integer.parseInt(request.getParameter("userid"));
        System.out.println(orderId);
        Order order = null;
        try {
            order = OrderService.getOrderById(orderId, connectionPool);
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        System.out.println(order);
        if (status.equals("APPROVED")) {
            Order finalOrder = order;
            executorService.submit(() -> {
                // Perform the long-running operation here
                try {
                    String savePath = getServletContext().getRealPath("/models/");
                    OrderService.approveOrder(finalOrder, savePath, connectionPool);
                } catch (DatabaseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            });
        } else if (status.equals("CANCELLED")) {
            try {
                OrderService.cancelOrder(order.getOrderID(), Status.CANCELLED, connectionPool);
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        } else if (status.equals("DELETED")) {
            try {
                OrderService.deleteOrderItems(order.getOrderID(), connectionPool);
                OrderService.deleteOrder(order.getOrderID(), connectionPool);
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("user", u);



        try {
            if (u != null && u.getRoleId() == 2) {
                User userinfo = UserService.getUser(userId, connectionPool);

                ArrayList<OrderItem> orderItemWood = new ArrayList<>();
                ArrayList<OrderItem> orderItemScrew = new ArrayList<>();
                ArrayList<OrderItem> orderItemFitting = new ArrayList<>();
                ArrayList<OrderItem> orderItemRoofTile = new ArrayList<>();

                for (OrderItem oi : order.getOrderItems()) {
                    if (oi.getMaterial() instanceof Wood) {
                        orderItemWood.add(oi);
                    } else if (oi.getMaterial() instanceof Screw) {
                        orderItemScrew.add(oi);
                    } else if (oi.getMaterial() instanceof Fitting) {
                        orderItemFitting.add(oi);
                    } else if (oi.getMaterial() instanceof RoofTile) {
                        orderItemRoofTile.add(oi);
                    }
                }

                request.setAttribute("orderbyid", order);
                request.setAttribute("orderItemWood", orderItemWood);
                request.setAttribute("orderItemScrew", orderItemScrew);
                request.setAttribute("orderItemFitting", orderItemFitting);
                request.setAttribute("orderItemRoofTile", orderItemRoofTile);
                request.setAttribute("user", userinfo);



                request.getRequestDispatcher("WEB-INF/admineditorder.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
