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
    request.getSession().setAttribute("PollManagement",false);
    request.getSession().setAttribute("user",false);
    request.getSession().setAttribute("vote",false);
    request.getSession().setAttribute("Poll",false);
    request.getSession().setAttribute("HiddenManagementSystem",false);
    request.getSession().setAttribute("updatePoll",false);
    request.getSession().setAttribute("accessPoll",false);
    request.getSession().setAttribute("listPolls",true);
    request.getSession().setAttribute("accessListPolls",false);

%>
<div class="container-fluid bg-light">
        <h3 style="text-align: center">List of Polls</h3>
        <div class="mb-4">
<%
    // todo iterate over polls retrieved from database, output them:
    // example:
    // Poll Name: pollName
    // Poll question: question
    // Poll choices: choice1,choice2, etc.
    // Poll current State: Running
%>

        </div>
</div>
</body>
</html>
