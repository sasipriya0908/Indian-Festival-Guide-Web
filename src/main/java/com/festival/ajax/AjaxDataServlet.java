package com.festival.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FestivalServlet")
public class AjaxDataServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String festival = request.getParameter("festival");
        if (festival == null || festival.trim().isEmpty()) {
            response.getWriter().println("<p>Please enter a festival name.</p>");
            return;
        }

        String[][] data = {
            {"Diwali", "Festival of Lights", "India", "Celebrated with diyas, rangoli, sweets and prayers."},
            {"Holi", "Festival of Colors", "India", "Celebrated with colors, music and spring festivities."},
            {"Navratri", "Nine Nights Festival", "India", "A festival of devotion, Garba and Dandiya."},
            {"Eid", "Festival of Breaking the Fast", "India", "Celebrated with prayers, charity, family gatherings and feasts."},
            {"Christmas", "Festival of Joy", "India", "Celebrated with Christmas trees, prayers, gifts and festive meals."},
            {"Ganesh Chaturthi", "Festival of Lord Ganesha", "India", "Celebrated with Ganesh idols, prayers, Modak and Visarjan."},
            {"Pongal", "Harvest Festival", "Tamil Nadu", "A harvest festival celebrated with Pongal, sugarcane and kolam."},
            {"Onam", "Harvest Festival", "Kerala", "Celebrated with Pookalam, Onam Sadya and Vallam Kali."},
            {"Raksha Bandhan", "Sibling Bond Festival", "India", "Celebrates the bond of love and protection between siblings."}
        };

        for (String[] row : data) {
            if (row[0].equalsIgnoreCase(festival.trim())) {
                response.getWriter().println("<div class='ajax-card'>");
                response.getWriter().println("<h3>" + row[0] + "</h3>");
                response.getWriter().println("<p><b>Type:</b> " + row[1] + "</p>");
                response.getWriter().println("<p><b>Region:</b> " + row[2] + "</p>");
                response.getWriter().println("<p><b>Description:</b> " + row[3] + "</p>");
                response.getWriter().println("</div>");
                return;
            }
        }

        response.getWriter().println(
            "<p>No festival found for: <b>" + escapeHtml(festival.trim()) + "</b></p>"
        );
    }

    private String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }
}
