<%@ page import="jakarta.servlet.http.HttpSession" %><%--
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

<%
    // creating a value held by the session so that the user has been visited
  request.getSession().setAttribute("user",true);
    request.getSession().setAttribute("vote",false);
    request.getSession().setAttribute("Poll",false);
    request.getSession().setAttribute("Choices","");
    request.getSession().setAttribute("DescriptionChoices","");
    %>
<br>
<div class="container-fluid bg-light">
    <form  action="PollApp"  method="Get">
        <h3 style="text-align: center">  Here is the form for the User creation</h3>
        <div class="mb-4">
            <label for="exampleInputID1" class="form-label">Enter the User ID</label>
            <input  name="UserID" type="text" class="form-control" id="exampleInputID1" aria-describedby="IDHelp">
        </div>
        <div class="mb-3">
            <label for="UserType">Choose a User type:</label>
            <select  class="form-select" aria-label="Default select example" name="UserType" id="UserType">
                <option value="RegularUser">RegularUser</option>
                <option value="PollManager">PollManager</option>
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
