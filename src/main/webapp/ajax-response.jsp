<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String festival = request.getParameter("festival");
    if (festival == null || festival.trim().isEmpty()) {
%>
Please enter a festival name.
<%
    } else {
%>
<div class="ajax-card">
    <h3><%= festival %></h3>
    <p>Response loaded dynamically from JSP using AJAX.</p>
    <p>The page was not refreshed.</p>
</div>
<%
    }
%>
