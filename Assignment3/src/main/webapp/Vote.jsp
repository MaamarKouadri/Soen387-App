<%@ page import="company.Choice" %>
<%@ page import="company.PollManager" %>
<%@ page import="java.util.StringJoiner" %><%--
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
    String pin = (String) session.getAttribute("PIN");
    String MyChoices ="";
    String DescriptionChoice ="";
    String[] arrODescriptionChoice;
    PollManager pm = (PollManager) session.getAttribute("PollObject");
    Choice[] choices = pm.getPoll().getChoices();
    StringBuilder sb = new StringBuilder();
    StringJoiner joiner = new StringJoiner(",");
    for (Choice c:choices) {
        joiner.add(c.getChoice());
    }
    MyChoices = joiner.toString();
    //MyChoices  = session.getAttribute("Choices").toString();
    arrODescriptionChoice = MyChoices.split(",");

    request.getSession().setAttribute("vote",true);
    request.getSession().setAttribute("PollManagement",false);
    request.getSession().setAttribute("user",false);
    request.getSession().setAttribute("Poll",false);
    request.getSession().setAttribute("HiddenManagementSystem",false);
    request.getSession().setAttribute("updatePoll",false);
    request.getSession().setAttribute("accessPoll",false);
    request.getSession().setAttribute("listPolls",false);
    request.getSession().setAttribute("accessListPolls",false);
    request.getSession().setAttribute("login",false);
%>
<jsp:include page="Header.jsp" />
<br>
<br>
<div class="container-fluid bg-light">
    <form action="PollApp"  method="Post">
        <h3 style="text-align: center">Vote Form</h3>
        <h4>Your PIN #: <% out.print(pin); %></h4>
        <h4>Poll ID: <% out.print(pm.getPoll().getUid()); %></h4>
        <h4>Question: <% if(pm.getPoll().getQuestion() != null) out.print(pm.getPoll().getQuestion());%></h4>
        <div class="mb-3">
            <label for="UserVoteChoice">Choose a choice amongst the ones entered for the poll:</label>
            <select  class="form-select" aria-label="Default select example" name="UserVoteChoice" id="UserVoteChoice">

                <%for (String s: arrODescriptionChoice){%>
                <option><%out.print(s);%></option>
                <%}
                    session.setAttribute("arrChoices", arrODescriptionChoice);
                %>
            </select>
        </div >
        <div class="d-grid gap-2">
            <button type="submit"  class="btn btn-Success ">Submit</button>
            <br>
        </div>
    </form>
</div>
<form action="PollApp"  method="Post">
    <input type="hidden" name="displayResults" value="true">
    <button type="submit"  class="btn btn-Success ">Display Results</button>
</form>
</body>
</html>
