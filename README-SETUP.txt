WEB FINAL - ECLIPSE SETUP

1. Extract Web_Final.zip.
2. Eclipse -> File -> Import -> Existing Projects into Workspace.
3. Select the extracted Web_Final folder.
4. Ensure Apache Tomcat 9.0 is selected as Targeted Runtime.
5. Ensure Java 17 is selected for the project.
6. Run the project on Tomcat.

Default URL:
http://localhost:8080/Web_Final/index.jsp

DATABASE:
Run DB_SETUP.sql in MySQL Workbench before using Registration or Festival Store.
Default MySQL settings: localhost:3306, user root, password root.
Change the password in DBConfig.java or use DB_PASSWORD / -Ddb.password.

FEATURE PAGES:
AJAX demo: ajax-demo.jsp
XML/XSD/XSLT feedback: feedback.jsp
XPath search: XPathSearchServlet?minRating=3
Festival store: ProductServlet
Registration: registration.jsp

SELENIUM:
SeleniumIFGTest.java is included under src/main/java/com/festival/selenium.
Add the Selenium Java JARs used in your Selenium practical to the project's Build Path.
The test uses Chrome by default and waits for ENTER at the end so the browser remains open.
