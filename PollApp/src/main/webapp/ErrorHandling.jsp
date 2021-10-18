<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.io.FileWriter" %>
<%@ page import="java.io.IOException" %>
<%@ page import="company.PollManager" isErrorPage="true" %><%--
  Created by IntelliJ IDEA.
  User: maama
  Date: 2021-10-16
  Time: 5:03 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>

    </style>

</head>
<body style="background-color: #dc143c">
<jsp:include page="Header.jsp" />
<%
    String Error =  session.getAttribute("ErrorMessage").toString();
%>
<br>
<div class="container-fluid bg-light">
    <h1> There is an Error </h1>
    <br>
    <h2> <% out.println(Error); %></h2>
</div>
</body>
</html>
