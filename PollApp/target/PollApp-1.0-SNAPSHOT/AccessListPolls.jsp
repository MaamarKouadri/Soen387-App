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
  request.getSession().setAttribute("listPolls",false);
  request.getSession().setAttribute("accessListPolls",true);
  request.getSession().setAttribute("login",false);
%>
<div class="container-fluid bg-light">
  <form  action="PollApp"  method="Post">
    <h3 style="text-align: center">Access List of Polls</h3>
    <div class="mb-4">
      <label for="exampleInputID" class="form-label">Please enter your user id:</label>
      <input  name="PollUserID" type="text" class="form-control" id="exampleInputID" >
    </div>

    <div class="d-grid gap-2">
      <button type="submit"  class="btn btn-Success ">Submit</button>
      <br>
    </div>
  </form>

</div>
</body>
</html>
