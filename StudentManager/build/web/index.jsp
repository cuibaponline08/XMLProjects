<%-- 
    Document   : Home
    Created on : Mar 23, 2016, 8:36:43 PM
    Author     : Gunner
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome <font color="red"><b>${sessionScope.fullname}</b></font> to my website</h1>
        <form action="CenterServlet" method="GET">
            <table>
                <tr>
                    <td>
                        <input type="text" name="txtSearch" value="${param.txtSearch}" />
                    </td>
                    <td><input type="Submit" name="btnAction" value="Search" /> </td>
                </tr>
                <tr>

                    <td colspan="1">
                        <c:url value="/CenterServlet" var="CreateLink">
                            <c:param name="btnAction" value="Create"></c:param>
                        </c:url>
                        <a href="${CreateLink}">Create new</a>

                    </td>
                </tr>
            </table>
        </form>
        <table border="1">
            <tr>
                <td>Product Name</td>
                <td>Image</td>
                <td>Price</td>
            </tr>
            <c:forEach items="${requestScope.rubiks}" var="item">
                <form action="CenterServlet" method="Get">
                    <tr>
                        <td><input type="text" name="txtFirstName" value="${item.productName}" /> </td>
                        <td><input type="text" name="txtLastName" value="${item.imageUrl}" /> </td>
                        <td><input type="text" name="txtClassName" value="${item.productPrice}" /> </td>
                        <td>
                            <c:url value="CenterServlet?btnAction=Delete" var="delLink">
                                <c:param name="txtIDSel" value="2"></c:param>
                            </c:url>
                            <a href="${delLink}">Delete</a>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </body>
</html>
