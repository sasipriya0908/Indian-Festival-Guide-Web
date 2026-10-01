package com.festival.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.festival.util.DBConfig;

@WebServlet("/ViewRegistrationServlet")
public class ViewRegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        try {

            Connection con = DBConfig.getConnection("indian_festival");

            String sql =
                "SELECT * FROM registrations";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ResultSet rs =
                ps.executeQuery();

            response.getWriter().println(
                "<html><head><title>Festival Registrations</title></head><body>"
            );

            response.getWriter().println(
                "<h1>Indian Festival Guide</h1>"
            );

            response.getWriter().println(
                "<h2>Registered Visitors</h2>"
            );

            response.getWriter().println(
                "<table border='1' cellpadding='10'>"
            );

            response.getWriter().println(
                "<tr>" +
                "<th>ID</th>" +
                "<th>Registration ID</th>" +
                "<th>Name</th>" +
                "<th>Email</th>" +
                "<th>Phone</th>" +
                "<th>Age</th>" +
                "<th>Festival</th>" +
                "</tr>"
            );

            while (rs.next()) {

                response.getWriter().println(
                    "<tr>" +

                    "<td>" +
                    rs.getInt("id") +
                    "</td>" +

                    "<td>" +
                    rs.getString("registration_id") +
                    "</td>" +

                    "<td>" +
                    rs.getString("full_name") +
                    "</td>" +

                    "<td>" +
                    rs.getString("email") +
                    "</td>" +

                    "<td>" +
                    rs.getString("phone") +
                    "</td>" +

                    "<td>" +
                    rs.getInt("age") +
                    "</td>" +

                    "<td>" +
                    rs.getString("favourite_festival") +
                    "</td>" +

                    "</tr>"
                );
            }

            response.getWriter().println(
                "</table>"
            );

            response.getWriter().println(
                "<br><a href='index.jsp'>Back to Home</a>"
            );

            response.getWriter().println(
                "</body></html>"
            );

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                "<h3>Database Error: "
                + e.getMessage()
                + "</h3>"
            );
        }
    }
}