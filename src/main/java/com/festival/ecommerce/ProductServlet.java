package com.festival.ecommerce;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Map<String, Object>> products = new ArrayList<Map<String, Object>>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM products");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> product = new HashMap<String, Object>();
                product.put("id", rs.getInt("id"));
                product.put("name", rs.getString("name"));
                product.put("festival", rs.getString("festival"));
                product.put("price", rs.getDouble("price"));
                product.put("image", rs.getString("image"));
                product.put("description", rs.getString("description"));
                products.add(product);
            }

            request.setAttribute("products", products);
            request.getRequestDispatcher("store.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h2>Database Error</h2>");
            response.getWriter().println("<p>" + e.getMessage() + "</p>");
        }
    }
}
