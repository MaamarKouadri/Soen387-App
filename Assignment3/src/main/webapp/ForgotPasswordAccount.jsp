<%--
  Created by IntelliJ IDEA.
  User: Nicolo pt 2
  Date: 2021-11-21
  Time: 5:59 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot Password page</title>
</head>
<%
    request.getSession().setAttribute("login",false);
    request.getSession().setAttribute("PollManagement",false);
    request.getSession().setAttribute("user",false);
    request.getSession().setAttribute("vote",false);
    request.getSession().setAttribute("Poll",false);
    request.getSession().setAttribute("HiddenManagementSystem",false);
    request.getSession().setAttribute("updatePoll",false);
    request.getSession().setAttribute("accessPoll",false);
    request.getSession().setAttribute("listPolls",false);
    request.getSession().setAttribute("accessListPolls",false);
    request.getSession().setAttribute("signUpAccount",false);
    request.getSession().setAttribute("forgotPasswordAccount",true);
%>
<body>
<jsp:include page="Header.jsp" />

<div class="container-fluid bg-light">
    <form action="PollApp"  method="Post">
        <h3 style="text-align: center">Forgot Password Form</h3>
        <div class="mb-4">
            <label for="email" class="form-label">Email:</label>
            <input  name="email" type="text" class="form-control" id="email" >
        </div>
        <div class="d-grid gap-2">
            <input type="submit" name="btn_forgot_password" class="form-control btn btn-dark" id="btn_forgot_password" value="Forgot Password">
            <br>
        </div>
    </form>
</div>
</body>
</html>
