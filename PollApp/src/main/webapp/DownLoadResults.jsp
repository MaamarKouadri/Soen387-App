<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.io.FileWriter" %>
<%@ page import="java.io.IOException" %><%--
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
<body>
<jsp:include page="Header.jsp" />

<br>
<div class="container-fluid bg-light">
    <form  action="PollApp"  method="Post">
        <h3 style="text-align: center">  Here is the form  to download the results</h3>
                     <br>

        <div class="d-grid gap-1">
            <button type="submit"  class="btn btn-dark ">Download</button>
            <br>
        </div>
    </form>

</div>
</body>
</html>
