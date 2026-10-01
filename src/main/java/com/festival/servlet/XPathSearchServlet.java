package com.festival.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

@WebServlet("/XPathSearchServlet")
public class XPathSearchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int minRating = 3;
        try {
            String value = request.getParameter("minRating");
            if (value != null) {
                minRating = Integer.parseInt(value);
            }
        } catch (NumberFormatException ignored) {
            minRating = 3;
        }

        if (minRating < 1) minRating = 1;
        if (minRating > 5) minRating = 5;

        response.setContentType("text/html;charset=UTF-8");

        try {
            String xmlPath = getServletContext().getRealPath("/xml/feedbacks.xml");
            File xmlFile = new File(xmlPath);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            XPath xpath = XPathFactory.newInstance().newXPath();
            String expression = "/feedbacks/feedback[rating>" + minRating + "]";
            NodeList nodes = (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);

            response.getWriter().println("<!DOCTYPE html>");
            response.getWriter().println("<html><head><title>XPath Feedback Search</title>");
            response.getWriter().println("<style>body{font-family:Arial;background:#fff8ed;margin:0;color:#333}.box{width:90%;max-width:950px;margin:40px auto;background:#fff;padding:25px;border-radius:12px;box-shadow:0 4px 14px rgba(0,0,0,.12)}table{width:100%;border-collapse:collapse}th,td{padding:10px;border:1px solid #ddd}th{background:#ff9933;color:#fff}a{color:#b33b00;font-weight:bold;text-decoration:none}</style>");
            response.getWriter().println("</head><body><div class='box'>");
            response.getWriter().println("<h1>XPath Conditional Search</h1>");
            response.getWriter().println("<p><strong>XPath:</strong> " + expression + "</p>");
            response.getWriter().println("<p>Feedbacks with rating greater than " + minRating + ": " + nodes.getLength() + "</p>");

            if (nodes.getLength() > 0) {
                response.getWriter().println("<table><tr><th>Name</th><th>Festival</th><th>Rating</th><th>Comments</th></tr>");
                for (int i = 0; i < nodes.getLength(); i++) {
                    org.w3c.dom.Element feedback = (org.w3c.dom.Element) nodes.item(i);
                    String name = text(feedback, "fullName");
                    String festival = text(feedback, "festival");
                    String rating = text(feedback, "rating");
                    String comments = text(feedback, "comments");
                    response.getWriter().println("<tr><td>" + escape(name) + "</td><td>" + escape(festival) + "</td><td>" + escape(rating) + " / 5</td><td>" + escape(comments) + "</td></tr>");
                }
                response.getWriter().println("</table>");
            } else {
                response.getWriter().println("<p>No matching feedback found.</p>");
            }

            response.getWriter().println("<p style='margin-top:20px'><a href='feedback.jsp'>Back to Feedback Form</a> | <a href='xml/feedbacks.xml'>View XSLT Summary</a></p>");
            response.getWriter().println("</div></body></html>");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h2>XPath Error</h2><p>" + escape(e.getMessage()) + "</p>");
        }
    }

    private static String text(org.w3c.dom.Element element, String tag) {
        NodeList list = element.getElementsByTagName(tag);
        return list.getLength() == 0 ? "" : list.item(0).getTextContent();
    }

    private static String escape(String value) {
        if (value == null) return "";
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}
