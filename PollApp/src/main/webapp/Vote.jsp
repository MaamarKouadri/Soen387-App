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
    <title>Title</title>
</head>
<body><%
    // creating a value held by the session so that the vote page has been visited
    request.getSession().setAttribute("vote",true);
    String MyChoices ="";
    String DescriptionChoice ="";
    String[] arrOfChoices = new String [4];
    String[] arrODescriptionChoice;


       // MyChoices = request.getParameter("Choices");
        //arrODescriptionChoice = MyChoices.split(",");
       MyChoices  = session.getAttribute("Choices").toString();
    arrODescriptionChoice = MyChoices.split(",");

    arrOfChoices[2] ="Argentina";
    arrOfChoices[1] ="Brazil";
    arrOfChoices[0] ="Peru";

       // arrOfChoices = MyChoices.split(",");

       // DescriptionChoice = request.getParameter("DescriptionChoice").toString();
        // arrODescriptionChoice = MyChoices.split(",");

    /*
     <option value = "RegularUser" > Choice 1 </option >
              <option value = "PollManager" > Choice 2 </option >
     */

    request.getSession().setAttribute("vote",true);
%>
<jsp:include page="Header.jsp" />
<br>
<br>
<div class="container-fluid bg-light">
    <form action="PollApp"  method="Get">
        <h3 style="text-align: center">  Here is the form where you can vote</h3>
        <div class="mb-4">
            <label for="exampleInputID1" class="form-label">Enter your User ID</label>
            <input  name="VoteUserID" type="text" class="form-control" id="exampleInputID1" aria-describedby="IDHelp">
        </div>
        <div class="mb-3">
            <label for="UserType">Choose a choice amongst the ones entered for the poll:</label>
            <select  class="form-select" aria-label="Default select example" name="VoteUserType" id="VoteUserType">

                <%for (String s: arrODescriptionChoice){%>
                <option><%out.print(s);%></option>
                <%}%>
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
