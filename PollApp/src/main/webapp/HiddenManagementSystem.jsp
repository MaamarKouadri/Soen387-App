<%@ page import="company.PollManager" %><%--
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
    /*
   ((PollManager) session.getAttribute("PollObject")).getPoll().getStatus();
    System.out.println("------------------------");
 System.out.println(status);
    System.out.println("------------------------");
     */

    request.getSession().setAttribute("HiddenManagementSystem",true);
    request.getSession().setAttribute("vote",false);
    request.getSession().setAttribute("PollManagement",false);
    request.getSession().setAttribute("user",false);
    request.getSession().setAttribute("Poll",false);
    request.getSession().setAttribute("updatePoll",false);
%>

<div class="container-fluid bg-light">
    <form  action="PollApp"  method="Post">
        <h3 style="text-align: center">  Poll Management System</h3>
        <br>
        <h4>The current poll state is <%   if(session.getAttribute("PollObject")!=null) out.println(((PollManager) session.getAttribute("PollObject")).getPoll().getStatus()); %>
            </h4>
        <div class="mb-3">
            <label for="PollAction">Action to take </label>
            <select  class="form-select" aria-label="Default select example" name="PollAction" id="PollAction">
                <option>Release Poll</option>
                <option>Unrelease Poll</option>
                <option>Run Poll</option>
                <option>Close Poll</option>
            </select>
        </div >
        <div class="d-grid gap-2">
            <button type="submit"  class="btn btn-Success ">Submit</button>
            <br>
        </div>
    </form>

</div>
</body>
</html>
