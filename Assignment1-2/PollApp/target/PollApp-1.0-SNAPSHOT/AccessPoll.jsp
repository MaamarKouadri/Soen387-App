<%@ page import="company.Choice" %><%--
  Created by IntelliJ IDEA.
  User: maama
  Date: 2021-10-16
  Time: 5:04 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
%>
<html>
<head>
    <title>Access Poll</title>
</head>
<body><%
    // creating a value held by the session so that the vote page has been visited
    request.getSession().setAttribute("vote",false);
    request.getSession().setAttribute("PollManagement",false);
    request.getSession().setAttribute("user",false);
    request.getSession().setAttribute("Poll",false);
    request.getSession().setAttribute("HiddenManagementSystem",false);
    request.getSession().setAttribute("updatePoll",false);
    request.getSession().setAttribute("accessPoll",true);
    request.getSession().setAttribute("listPolls",false);
    request.getSession().setAttribute("accessListPolls",false);
    request.getSession().setAttribute("login",false);
%>
<jsp:include page="Header.jsp" />
<br>
<br>
<div class="container-fluid bg-light">
    <form action="PollApp"  method="Post">
        <h3 style="text-align: center">Access Poll Form</h3>
        <div class="mb-4">
            <label for="exampleInputID1" class="form-label">Enter Poll ID</label>
            <input  name="PollID" type="text" class="form-control" id="exampleInputID1" aria-describedby="IDHelp">
        </div>

        <div class="mb-4">
            <label for="exampleInputID2" class="form-label">Enter your PIN# (optional)</label>
            <input  name="PIN" type="text" class="form-control" id="exampleInputID2" aria-describedby="IDHelp">
        </div>

        <div class="d-grid gap-2">
            <button type="submit"  class="btn btn-Success ">Submit</button>
            <br>
        </div>
    </form>
</div>
</body>
</html>
