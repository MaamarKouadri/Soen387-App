<%@ page import="company.PollManager" %><%--
  Created by IntelliJ IDEA.
  User: maama
  Date: 2021-10-16
  Time: 5:05 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <style>
        #piechart_3d{
            position: relative;
            top:0%;
            left: 11%;
        }
        #CenterDiv{
            position: relative;
            width:27%;
            left:37%;
        }
    </style>
    <script type="text/javascript">
        let Choices = '<%=  session.getAttribute("FinalArray").toString() %>';
        let ChoiceArr = Choices.split(",");
        let Choices2 = '<%=  session.getAttribute("FinalArray2").toString() %>';
        let ChoiceArr2 = Choices2.split(",");
        console.log("----------------------------------------------");
        console.log(Choices);
        console.log(ChoiceArr);
        console.log(Choices2);
        console.log(ChoiceArr2);
        console.log("----------------------------------------------");
        </script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load("current", {packages:["corechart"]});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
            /*
            let ChoiceArr2 = ["Zidane","Ronlado"];
            let ChoiceArr3 = ["1","2"];

            var dataRows = new Array();
            dataRows[0] = [ 'First', 'Second'];
            for (var i = 0; i < ChoiceArr2.length; i++){
                dataRows[i + 1] = [  ChoiceArr2[i], ChoiceArr2[i] ];
            }
//second parameter is false because first row is headers, not data.
            var data = google.visualization.arrayToDataTable(dataRows, false);

           // var data = google.visualization.arrayToDataTable(dataRows);

          */

           let ChoiceArr5 =  [['Choice', 'Selected Percentage'],];
            let ChoiceArr3 =[];

           for(let i = 1 ; i <ChoiceArr.length ; i++ ){
               ChoiceArr5.push([ChoiceArr[i],parseInt(ChoiceArr2[i])]);
           }
           console.log(ChoiceArr3);
            console.log(ChoiceArr5);
            var data = google.visualization.arrayToDataTable(ChoiceArr5);
/*
            var data = google.visualization.arrayToDataTable([


                ['Task', 'Hours per Day'],
                ['Work',     11],
                ['Eat',      2],
                ['Commute',  2],
                ['Watch TV', 2],
                ['Sleep',    7]

            ]);
            */
            var options = {
                title: 'Poll Results',
                is3D: true,
            };

            var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>
<jsp:include page="Header.jsp" />
<div id="piechart_3d"  style="width: 1200px; height: 700px;">
    <h3 style="alignment: center"> Poll Results </h3>
</div>

<form id="CenterDiv" action="PollApp"  method="Get">
    <button type="submit" style="position: center" style="align-content: center"  class="btn btn-dark ">Click Here to Download the Results</button>
</form>
</body>
</html>

<%
    //Clearing the Poll after Results have been displayed.
    ((PollManager) session.getAttribute("PollObject")).ClearPoll();
    %>