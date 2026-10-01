XML Feedback Feature
====================

5 inputs:
1. Full Name
2. Email
3. Favourite Festival
4. Rating
5. Comments / Suggestions

Flow:
index.jsp footer -> feedback.jsp -> FeedbackServlet -> xml/feedbacks.xml

Validation:
feedbacks.xml is validated against xml/feedbacks.xsd before and after a new feedback is appended.

XSLT:
feedbacks.xml references ../feedback-summary.xsl and displays an HTML summary in the browser.

XPath:
XPathSearchServlet?minRating=3 uses /feedbacks/feedback[rating>3].
