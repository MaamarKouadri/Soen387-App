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

<div class="container-fluid bg-light">
    <form  action="HiddenManagementSystem.jsp" >
        <h3 style="text-align: center">  Poll Management System</h3>
        <div class="mb-4">
            <label for="PollManagementSystem" class="form-label">Enter the PassCode </label>
            <input  name="PollManagementID" type="text" class="form-control" id="PollManagementSystem" aria-describedby="IDHelp">
        </div>
        <div class="d-grid gap-2">
            <button type="submit"  class="btn btn-Success ">Submit</button>
            <br>
        </div>
    </form>

</div>
</body>
</html>
