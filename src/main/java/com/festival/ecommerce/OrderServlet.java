package com.festival.ecommerce;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        if (name == null || name.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || address == null || address.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Name, email and address are required.");
            return;
        }

        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<Map<String, String>> cart =
                (List<Map<String, String>>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h2>Your cart is empty!</h2>");
            response.getWriter().println("<a href='ProductServlet'>Continue Shopping</a>");
            return;
        }

        String sql = "INSERT INTO orders "
                + "(customer_name, email, address, product_name, quantity, total_price) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (Map<String, String> item : cart) {
                String productName = item.get("name");
                double price = Double.parseDouble(item.get("price"));

                ps.setString(1, name.trim());
                ps.setString(2, email.trim());
                ps.setString(3, address.trim());
                ps.setString(4, productName);
                ps.setInt(5, 1);
                ps.setDouble(6, price);
                ps.executeUpdate();
            }

            session.removeAttribute("cart");
            response.sendRedirect("order-success.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h2>Order Processing Error</h2>");
            response.getWriter().println("<p>" + e.getMessage() + "</p>");
        }
    }
}
