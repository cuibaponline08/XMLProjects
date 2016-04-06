<%-- 
    Document   : createnew
    Created on : Mar 23, 2016, 9:28:38 PM
    Author     : Gunner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="CenterServlet" method="GET">
            <table>
                <tr>
                    <td>ID name</td>
                    <td><input type="text" name="txtID" value="" /> </td>
                </tr>
                <tr>
                    <td>First name</td>
                    <td><input type="text" name="txtFirstName" value="" /> </td>
                </tr>
                <tr>
                    <td>Last name</td>
                    <td><input type="text" name="txtLastName" value="" /> </td>
                </tr>
                <tr>
                    <td>Class</td>
                    <td><input type="text" name="txtClassName" value="" /> </td>
                </tr>
                <tr>
                    <td>DOB</td>
                    <td><input type="text" name="txtBirthDay" value="" /> </td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="txtPassword" value="" /> </td>
                </tr>
                <tr>
                    <td><input type="Submit" name="btnAction" value="CreateNew" /> </td>
                    <td><input type="Reset" name="" value="Reset" /> </td>
                </tr>

            </table>
        </form>
    </body>
</html>
