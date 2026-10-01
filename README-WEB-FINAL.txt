WEB FINAL - INDIAN FESTIVAL GUIDE
=================================

This is the integrated final web application. The original festival UI/assets are retained.
The project combines:
1. JSP + Servlet registration
2. MySQL database connectivity
3. Festival e-commerce store
4. XML + XSD + XSLT feedback
5. XPath XML search
6. AJAX dynamic data loading from Servlet, XML and JSP
7. Selenium Java testing

TECHNOLOGY
----------
HTML, CSS, JavaScript, JSP, Servlet, XML, XSD, XSLT, AJAX, MySQL, JDBC, Selenium, Tomcat 9, Java 17.

PROJECT NAME
------------
Web_Final

DEFAULT URL
-----------
http://localhost:8080/Web_Final/index.jsp

FEATURE URLS
------------
Registration: http://localhost:8080/Web_Final/registration.jsp
Registration records: http://localhost:8080/Web_Final/ViewRegistrationServlet
Festival Store: http://localhost:8080/Web_Final/ProductServlet
Checkout: http://localhost:8080/Web_Final/checkout.jsp
Feedback/XML/XSD/XSLT: http://localhost:8080/Web_Final/feedback.jsp
XPath search: http://localhost:8080/Web_Final/XPathSearchServlet?minRating=3
AJAX demo: http://localhost:8080/Web_Final/ajax-demo.jsp
XML data: http://localhost:8080/Web_Final/festival-data.xml

ECLIPSE IMPORT
--------------
1. Extract this ZIP.
2. Eclipse -> File -> Import -> Existing Projects into Workspace.
3. Select the Web_Final folder.
4. Target runtime: Apache Tomcat 9.0.
5. Java: 17.
6. Run the project on Tomcat.

DATABASE SETUP
--------------
1. Open DB_SETUP.sql in MySQL Workbench.
2. Run the complete script.
3. It creates:
   - indian_festival.registrations
   - indian_festival_store.products
   - indian_festival_store.orders
4. Default database settings are:
   host=localhost
   port=3306
   user=root
   password=root

Changing DB password:
- Edit DB password in one place using environment variable DB_PASSWORD, or
- start Java/Tomcat with -Ddb.password=YOUR_PASSWORD.
DBConfig.java also supports DB_HOST, DB_PORT and DB_USER.

E-COMMERCE FLOW
---------------
index.jsp -> ProductServlet -> store.jsp -> cart.jsp -> checkout.jsp -> OrderServlet -> order-success.jsp

XML FLOW
--------
feedback.jsp -> FeedbackServlet -> feedbacks.xml + XSD validation -> XSLT summary
XPathSearchServlet reads feedbacks.xml using an XPath condition.

AJAX FLOW
---------
ajax-demo.jsp -> ajax-demo.js ->
  FestivalServlet (Servlet response)
  festival-data.xml (XML response)
  ajax-response.jsp (JSP response)

SELENIUM
--------
Source: src/main/java/com/festival/selenium/SeleniumIFGTest.java
Default URL is /Web_Final/index.jsp.
The test runs slowly for demonstration and waits for ENTER before ending.

Selenium covers:
- Browsers
- URL, title, page source
- Refresh, navigate, back, forward
- SwitchTo / Alert / Confirm
- isSelected, isDisplayed, isEnabled
- click, clear, sendKeys, getText, getAttribute
- findElement, findElements
- id, name, className, linkText, tagName, cssSelector, xpath

Selenium dependencies are not bundled because they are normally added through
Eclipse/Maven or the Selenium JARs already used for the practical.
The MySQL Connector/J JAR is already included in WEB-INF/lib.

IMPORTANT
---------
The visual UI files such as index.jsp, style.css, script.js and the festival pages/assets
are retained from the existing Indian Festival Guide project. AJAX is provided as an
additional page so the main UI layout is not redesigned.
