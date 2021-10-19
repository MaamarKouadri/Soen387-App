<%--
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
</head>
<body>
<%
    // creating a value held by the session so that the Create Poll Page has been visited
    request.getSession().setAttribute("Poll",true);
    request.getSession().setAttribute("HiddenManagementSystem",false);
    request.getSession().setAttribute("vote",false);
    request.getSession().setAttribute("PollManagement",false);
    request.getSession().setAttribute("user",false);
%>
<jsp:include page="Header.jsp" />

<br>
<br>
<div class="container-fluid bg-light">
    <form action="PollApp"  method="Post">
        <h3 style="text-align: center">Create Poll Form</h3>
        <div class="mb-4">
            <label for="exampleInputID" class="form-label">Please enter your pre-defined pass-code:</label>
            <input  name="PollUserID" type="text" class="form-control" id="exampleInputID" >
        </div>
        <div class="mb-4">
            <label for="exampleInputName" class="form-label">Enter the name of the Poll:</label>
            <input  name="PollName" type="text" class="form-control" id="exampleInputName" >
        </div>

        <div class="mb-4">
            <label for="exampleInputQuestion" class="form-label">Enter the question for your Poll:</label>
            <input  name="PollQuestion" type="text" class="form-control" id="exampleInputQuestion" >
        </div>

        <div class="mb-4">
            <label for="exampleInputChoice" class="form-label">Enter the choices for this poll separated by comma (i.e: choice1,choice2,choice3,etc.):</label>
            <input  name="PollChoice" type="text" class="form-control" id="exampleInputChoice" >
        </div>

        <div class="mb-4">
            <label for="exampleInputDescription" class="form-label">Enter the description of the choices seperated by comma (i.e: description1,description2,description3,etc.) </label>
            <input  name="Description" type="text" class="form-control" id="exampleInputDescription" >
        </div>

        <div class="d-grid gap-2">
            <button type="submit"  class="btn btn-Success ">Submit</button>
            <br>
        </div>
    </form>

</div>
</body>
</html>
