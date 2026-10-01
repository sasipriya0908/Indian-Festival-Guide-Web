<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/feedbacks">
        <html>
        <head>
            <title>Indian Festival Guide - Feedback Summary</title>
            <style>
                body{font-family:Arial,sans-serif;background:#fff8ed;margin:0;color:#333}
                header{background:#cc5500;color:white;padding:25px;text-align:center}
                .wrap{width:90%;max-width:1100px;margin:30px auto}
                table{width:100%;border-collapse:collapse;background:white;box-shadow:0 4px 14px rgba(0,0,0,.12)}
                th,td{padding:12px;border:1px solid #ddd;text-align:left}
                th{background:#ff9933;color:white}
                tr:nth-child(even){background:#fff4df}
                .empty{text-align:center;padding:30px;background:white;border-radius:10px}
                a{color:#b33b00;font-weight:bold;text-decoration:none}
            </style>
        </head>
        <body>
            <header>
                <h1>Indian Festival Guide</h1>
                <p>XML Feedback Database Summary</p>
            </header>
            <div class="wrap">
                <h2>Feedback Summary</h2>
                <p><strong>Total Feedbacks:</strong> <xsl:value-of select="count(feedback)"/></p>
                <xsl:choose>
                    <xsl:when test="count(feedback) &gt; 0">
                        <table>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Festival</th>
                                <th>Rating</th>
                                <th>Comments</th>
                            </tr>
                            <xsl:for-each select="feedback">
                                <tr>
                                    <td><xsl:value-of select="@id"/></td>
                                    <td><xsl:value-of select="fullName"/></td>
                                    <td><xsl:value-of select="email"/></td>
                                    <td><xsl:value-of select="festival"/></td>
                                    <td><xsl:value-of select="rating"/> / 5</td>
                                    <td><xsl:value-of select="comments"/></td>
                                </tr>
                            </xsl:for-each>
                        </table>
                    </xsl:when>
                    <xsl:otherwise>
                        <div class="empty">No feedback has been submitted yet.</div>
                    </xsl:otherwise>
                </xsl:choose>
                <p style="margin-top:25px"><a href="../feedback.jsp">Back to Feedback Form</a> | <a href="../XPathSearchServlet?minRating=3">Search ratings greater than 3</a></p>
            </div>
        </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
