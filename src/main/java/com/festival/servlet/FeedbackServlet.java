package com.festival.servlet;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String festival = request.getParameter("festival");
        String ratingText = request.getParameter("rating");
        String comments = request.getParameter("comments");

        if (isBlank(fullName) || isBlank(email) || isBlank(festival)
                || isBlank(ratingText) || isBlank(comments)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All 5 feedback fields are required.");
            return;
        }

        int rating;
        try {
            rating = Integer.parseInt(ratingText);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Rating must be a number from 1 to 5.");
            return;
        }

        if (rating < 1 || rating > 5) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Rating must be between 1 and 5.");
            return;
        }

        try {
            String xmlPath = getServletContext().getRealPath("/xml/feedbacks.xml");
            String xsdPath = getServletContext().getRealPath("/xml/feedbacks.xsd");

            File xmlFile = new File(xmlPath);
            File xsdFile = new File(xsdPath);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            // Validate the XML document against XSD before updating it.
            validate(document, xsdFile);

            Element root = document.getDocumentElement();
            Element feedback = document.createElement("feedback");
            feedback.setAttribute("id", "F" + System.currentTimeMillis());

            appendElement(document, feedback, "fullName", fullName.trim());
            appendElement(document, feedback, "email", email.trim());
            appendElement(document, feedback, "festival", festival.trim());
            appendElement(document, feedback, "rating", String.valueOf(rating));
            appendElement(document, feedback, "comments", comments.trim());

            root.appendChild(feedback);

            // Validate the updated XML against the XSD.
            validate(document, xsdFile);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new javax.xml.transform.dom.DOMSource(document),
                    new StreamResult(xmlFile));

            response.sendRedirect("feedback.jsp?success=true");

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h2>Feedback Error</h2>");
            response.getWriter().println("<p>" + escapeHtml(e.getMessage()) + "</p>");
        }
    }

    private static void appendElement(Document document, Element parent, String name, String value) {
        Element element = document.createElement(name);
        element.appendChild(document.createTextNode(value));
        parent.appendChild(element);
    }

    private static void validate(Document document, File xsdFile) throws Exception {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        schemaFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        schemaFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        Schema schema = schemaFactory.newSchema(xsdFile);
        schema.newValidator().validate(new javax.xml.transform.dom.DOMSource(document));
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static String escapeHtml(String value) {
        if (value == null) return "Unknown error";
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;");
    }
}
