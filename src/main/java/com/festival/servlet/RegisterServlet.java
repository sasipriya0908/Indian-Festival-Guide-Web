package com.festival.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.festival.util.DBConfig;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String registrationId =
                request.getParameter("registrationId");

        String fullName =
                request.getParameter("fullName");

        String email =
                request.getParameter("email");

        String phone =
                request.getParameter("phone");

        int age =
                Integer.parseInt(request.getParameter("age"));

        String favouriteFestival =
                request.getParameter("favouriteFestival");

        try {

            Connection con = DBConfig.getConnection("indian_festival");

            String sql =
                "INSERT INTO registrations " +
                "(registration_id, full_name, email, phone, age, favourite_festival) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setString(1, registrationId);
            ps.setString(2, fullName);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setInt(5, age);
            ps.setString(6, favouriteFestival);

            ps.executeUpdate();

            ps.close();
            con.close();

            response.sendRedirect(
                "success.jsp?registrationId="
                + registrationId
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                "Database Error: " + e.getMessage()
            );
        }
    }
}