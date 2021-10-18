<%--
  Created by IntelliJ IDEA.
  User: maama
  Date: 2021-10-16
  Time: 4:38 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>

<div class="container-fluid">
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">

            <a  class="btn btn-dark  btn-lg "  role="button" data-bs-toggle="button" onclick="window.location='User.jsp'">Create User </a>
            <a href="PollCreation.jsp"  class="btn btn-dark  btn-lg"  role="button" data-bs-toggle="button" onclick="window.location='PollCreation.jsp'">Create Poll</a>
            <a href="PollCreation.jsp"  class="btn btn-dark  btn-lg"  role="button" data-bs-toggle="button" onclick="window.location='PollManagment.jsp'">Poll Management</a>
            <a href="Vote.jsp"  class="btn btn-dark  btn-lg"  role="button" data-bs-toggle="button" onclick="window.location='Vote.jsp'">Vote</a>

        </div>
    </nav>
</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
