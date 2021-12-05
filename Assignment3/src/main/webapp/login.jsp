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
    <title>Login page</title>
</head>
<%
    request.getSession().setAttribute("login",true);
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
    request.getSession().setAttribute("forgotPasswordAccount",false);
    request.getSession().setAttribute("changePasswordAccount",false);
    request.getSession().setAttribute("changePasswordForgetAccount",false);
%>
<body>
<jsp:include page="Header.jsp" />

<div class="container-fluid bg-light">
    <form action="PollApp"  method="Post">
        <h3 style="text-align: center">User Management</h3>
        <div class="d-grid gap-2">
            <input type="submit" name="btn_sign_up" class="form-control btn btn-dark" id="btn_sign_up" value="Sign Up">
            <br>
        </div>
        <div class="d-grid gap-2">
            <input type="submit" name="btn_forgot_password" class="form-control btn btn-dark" id="btn_forgot_password" value="Forgot Password?">
            <br>
        </div>
    </form>
</div>
</body>
</html>
