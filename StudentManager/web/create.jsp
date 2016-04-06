<%-- 
    Document   : create
    Created on : Mar 24, 2016, 9:36:19 AM
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
        <form action="CenterServlet">
            <table>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="txtFirstName" value="" /> </td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="txtLastName" value="" /> </td>
                </tr>
                <tr>
                    <td>Class Name</td>
                    <td><input type="text" name="txtClassName" value="" /> </td>
                </tr>
                <tr>
                    <td>Birthday</td>
                    <td><input type="text" name="txtBirthDay" value="" /> </td>
                </tr>
            </table>
        </form>
    </body>
</html>
