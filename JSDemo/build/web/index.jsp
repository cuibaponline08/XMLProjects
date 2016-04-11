<%-- 
    Document   : index
    Created on : Apr 8, 2016, 4:31:19 PM
    Author     : cuiba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/main.js"></script>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <table border="1">
            <tr id="tr1">
                <td>aaaa</td>
                <td>bbbb</td>
            </tr>
            <tr id="tr2">
                <td>aaaa</td>
                <td>bbbb</td>
            </tr>
            <tr id="tr3">
                <td>aaaa</td>
                <td>bbbb</td>
            </tr>
            <tr id="tr4">
                <td>aaaa</td>
                <td>bbbb</td>
            </tr>
        </table>
        <form>
            <h2>Visibility</h2>
            <input type="button" name="V1" onclick="changeVisibility('tr1')" />
            <input type="button" name="V2" onclick="changeVisibility('tr2')" />
            <input type="button" name="V3" onclick="changeVisibility('tr3')" />
            <input type="button" name="V4" onclick="changeVisibility('tr4')" />
            <h2>Display</h2>
            <input type="button" name="V1" onclick="changeDisplay('tr1')" />
            <input type="button" name="V2" onclick="changeDisplay('tr2')" />
            <input type="button" name="V3" onclick="changeDisplay('tr3')" />
            <input type="button" name="V4" onclick="changeDisplay('tr4')" />
        </form>
    </body>
</html>
