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
%>
<body>
<jsp:include page="Header.jsp" />

<div class="container-fluid bg-light">
    <form action="PollApp"  method="Post">
        <h3 style="text-align: center">Login Form</h3>
        <div class="mb-4">
            <label for="username" class="form-label">Username:</label>
            <input  name="username" type="text" class="form-control" id="username" >
        </div>
        <div class="mb-4">
            <label for="password" class="form-label">Password:</label>
            <input type="password" name="password" class="form-control" id="password" >
        </div>
        <div class="d-grid gap-2">
            <input type="submit" name="btn_login" class="form-control btn btn-dark" id="btn_login" value="Login">
            <br>
        </div>
        <div class="d-grid gap-2">
            <input type="submit" name="btn_sign_up" class="form-control btn btn-dark" id="btn_sign_up" value="Sign Up">
            <br>
        </div>
        <div class="d-grid gap-2">
            <input type="submit" name="btn_edit_account" class="form-control btn btn-dark" id="btn_edit_account" value="Edit Account">
            <br>
        </div>
    </form>
</div>
</body>
</html>
