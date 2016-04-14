<%-- 
    Document   : index
    Created on : Apr 14, 2016, 4:05:37 PM
    Author     : cuiba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            var xmlHttpRequest = new XMLHttpRequest();

            function findTutorial() {
                xmlHttpRequest.open("POST", "Controller?stringSearch=" +
                        document.getElementById("topicTextInput").value, true);
                xmlHttpRequest.send();
                xmlHttpRequest.onreadystatechange = processTutorial;
            }

            function processTutorial() {
                if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
                    var table = document.getElementById('tutorialTable');
                    var parser = new DOMParser();
                    var dom = parser.parseFromString(xmlHttpRequest.responseText    ,
                            "text/xml");
                    var name = dom.getElementsByTagName("name");
                    var tutorial = dom.getElementsByTagName("tutorial");
                    var headRow = table.insertRow(0);
                    var headCell = headRow.insertCell(0);
                    headCell.style.backgroundColor = "LightGray";
                    headCell.innerHTML = name[0].childNodes[0].nodeValue;
                    var i = 0;
                    while (i < tutorial.length) {
                        row = table.insertRow(i + 1);
                        cell = row.insertCell(0);
                        cell.innerHTML = tutorial[i++].childNodes[0].nodeValue;
                    }
                }
            }
        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
        <input type="text" id="topicTextInput"/>
        <button type="button" onclick="findTutorial()">Find tutorial</button>

        <table id="tutorialTable">

        </table>
    </body>
</html>
