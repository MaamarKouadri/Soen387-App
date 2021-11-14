<%--
  Created by IntelliJ IDEA.
  User: maama
  Date: 2021-10-18
  Time: 4:03 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="Header.jsp" />
<%
    request.getSession().setAttribute("PollManagement",true);
    request.getSession().setAttribute("user",false);
    request.getSession().setAttribute("vote",false);
    request.getSession().setAttribute("Poll",false);
    request.getSession().setAttribute("HiddenManagementSystem",false);
    request.getSession().setAttribute("updatePoll",false);
    request.getSession().setAttribute("accessPoll",false);
    request.getSession().setAttribute("listPolls",false);
    request.getSession().setAttribute("accessListPolls",false);
%>
<div class="container-fluid bg-light">
    <form  action="PollApp"  method="Post">
        <h3 style="text-align: center">  Poll Management System</h3>
        <div class="mb-4">
            <label for="PollManagementUserID" class="form-label">Enter the PassCode </label>
            <input  name="PollManagementUserID" type="text" class="form-control" id="PollManagementUserID" aria-describedby="IDHelp">
            <label for="PollManagementSystemID" class="form-label">Enter the PollID </label>
            <input  name="PollManagementSystemID" type="text" class="form-control" id="PollManagementSystemID" aria-describedby="IDHelp">
        </div>
        <div class="d-grid gap-2">
            <button type="submit"  class="btn btn-Success ">Submit</button>
            <br>
        </div>
    </form>

</div>
</body>
</html>
